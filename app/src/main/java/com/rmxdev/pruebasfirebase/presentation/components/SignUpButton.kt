package com.rmxdev.pruebasfirebase.presentation.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmxdev.pruebasfirebase.R
import com.rmxdev.pruebasfirebase.ui.theme.BackgroundButton
import com.rmxdev.pruebasfirebase.ui.theme.ShapeButton

@Composable
fun SignUpButton(modifier: Modifier, painter: Painter, title: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp)
            .background(BackgroundButton)
            .border(2.dp, ShapeButton, CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painter,
            contentDescription = "Google logo",
            modifier = Modifier
                .padding(16.dp)
                .size(16.dp)
        )
        Text(
            text = "Continue with $title",
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}