package com.rmxdev.pruebasfirebase.presentation.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.pruebasfirebase.R
import com.rmxdev.pruebasfirebase.presentation.components.SignUpButton
import com.rmxdev.pruebasfirebase.ui.theme.Black
import com.rmxdev.pruebasfirebase.ui.theme.Gray
import com.rmxdev.pruebasfirebase.ui.theme.Green

@Composable
fun InitialScreen(modifier: Modifier = Modifier, navigateToLogin: () -> Unit, navigateToSignUp: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 800f)),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.spotify),
            contentDescription = "Spotify logo",
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Millions of songs.",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Free on Spotify.",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navigateToSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Sign up free", color = Black)
        }
        Spacer(modifier = Modifier.height(8.dp))
        SignUpButton(Modifier.clickable {  }, painterResource(id = R.drawable.google), title = "Google")
        Spacer(modifier = Modifier.height(8.dp))
        SignUpButton(Modifier.clickable {  }, painterResource(id = R.drawable.facebook), title = "Facebook")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Log In", color = Color.White, modifier = Modifier.clickable { navigateToLogin() })
        Spacer(modifier = Modifier.weight(1f))
    }
}