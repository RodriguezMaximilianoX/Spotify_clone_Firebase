package com.rmxdev.pruebasfirebase.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rmxdev.pruebasfirebase.R
import com.rmxdev.pruebasfirebase.model.Artist
import com.rmxdev.pruebasfirebase.model.Player
import com.rmxdev.pruebasfirebase.ui.theme.Black
import com.rmxdev.pruebasfirebase.ui.theme.Purple40

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = HomeViewModel()) {

    val artists = viewModel.artist.collectAsState()
    val player by viewModel.player.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Text(
            text = "Popular artist",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(artists.value) {
                ArtistItem(
                    artist = it,
                    onItemSelected = {
                        viewModel.addPlayer(it)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        player?.let {
            PlayerComponent(
                player = it,
                onPlaySelected = { viewModel.onPlaySelected() },
                onCloseSelected = { viewModel.onCloseSelected() }
            )
        }
    }
}

@Composable
fun PlayerComponent(player: Player, onPlaySelected: () -> Unit, onCloseSelected: () -> Unit) {
    val icon = if (player.play == true) R.drawable.ic_pause else R.drawable.ic_play
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Purple40),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = player.artist?.name.orEmpty(),
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Color.White
        )
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "play/pause",
            modifier = Modifier
                .size(40.dp)
                .clickable { onPlaySelected() }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier
                .size(40.dp)
                .clickable { onCloseSelected() }
        )
    }
}

@Composable
fun ArtistItem(modifier: Modifier = Modifier, artist: Artist, onItemSelected: (Artist) -> Unit) {
    Column(
        modifier = modifier.clickable { onItemSelected(artist) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = artist.image,
            contentDescription = "Imagen del artista",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(), color = Color.White)
    }
}
/*    val random = (1..1000).random()
    val artist = Artist(name = "Artista $random", numberOfSongs = random)
    db.collection("artists")
        .add(artist) // Agrega el documento a la colección "artists"
        .addOnSuccessListener {
            Log.i(
                "Home",
                "Documento creado correctamente"
            )
        } // Se llama si se agrega correctamente
        .addOnFailureListener { Log.i("Home", "Fallo al crear el documento") } // Se llama si falla
        .addOnCompleteListener {
            Log.i(
                "Home",
                "Completado"
            )
        } // Se llama siempre que se complete de manera correcta o incorrecta
}*/