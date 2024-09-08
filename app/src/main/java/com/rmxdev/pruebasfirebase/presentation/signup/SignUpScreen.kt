package com.rmxdev.pruebasfirebase.presentation.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.rmxdev.pruebasfirebase.R
import com.rmxdev.pruebasfirebase.ui.theme.Black
import com.rmxdev.pruebasfirebase.ui.theme.Green
import com.rmxdev.pruebasfirebase.ui.theme.SelectedField
import com.rmxdev.pruebasfirebase.ui.theme.UnselectedField

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(32.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            tint = Color.White,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(32.dp)
        )
        Text(text = "Email", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(text = "Password", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //Navegar a Home
                        Log.i("SignUp", "SignUp correcto")
                    } else {
                        Log.i("SignUp", "SignUp incorrecto")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Sign Up", color = Black)
        }
    }
}