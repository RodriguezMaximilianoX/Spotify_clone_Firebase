package com.rmxdev.pruebasfirebase.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rmxdev.pruebasfirebase.model.Artist
import com.rmxdev.pruebasfirebase.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var database = Firebase.database
    private var db = Firebase.firestore
    private val _artist = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> =
        _artist //Con esto solamente se puede leer desde fuera del viewModel y modificar solo desde dentro
    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player

    init {
        getArtist()
        getPLayer()
    }

    private fun getPLayer() {
        viewModelScope.launch {
            collectPlayer().collect {
                val player = it.getValue(Player::class.java)
                _player.value = player
            }
        }
    }

    private fun getArtist() {
        viewModelScope.launch {
            val result: List<Artist> = withContext(Dispatchers.IO) {
                getAllArtists()
            }
            _artist.value = result
        }
    }

    private suspend fun getAllArtists(): List<Artist> {
        return try {
            db.collection("artists")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Artist::class.java)
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // En este caso no es una suspend fun ya que los Flow no son corrutinas
    // Se utiliza callbackFlow para obtener los datos de la base de datos en tiempo real
    private fun collectPlayer(): Flow<DataSnapshot> = callbackFlow {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("RealTimeDatabase", "Error: ${error.message}")
                close(error.toException())
            }

        }

        val ref = database.reference.child("player")
        ref.addValueEventListener(listener)

        //Si funciona bien y se cierra la pantalla se ejecuta esto
        awaitClose{
            ref.removeEventListener(listener)
        }
    }

    fun onPlaySelected() {
        if(player.value != null){
            val currentPlayer = _player.value?.copy(play = !_player.value?.play!!)
            val ref = database.reference.child("player")
            ref.setValue(currentPlayer)
        }
    }

    fun onCloseSelected() {
        val ref = database.reference.child("player")
        ref.setValue(null)
    }

    fun addPlayer(artist: Artist) {
        val ref = database.reference.child("player")
        val player = Player(artist = artist, play = true)
        ref.setValue(player)
    }
}