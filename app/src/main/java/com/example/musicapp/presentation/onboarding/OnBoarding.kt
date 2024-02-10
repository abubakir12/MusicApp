package com.example.musicapp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.presentation.theme.GrayMusic

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier, navigateToHomeScreen: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().background(GrayMusic),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.on_boarding),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
        Button(
            modifier = modifier
                .padding(horizontal = 40.dp)
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomCenter)
                ,
            onClick = {
                navigateToHomeScreen()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.orange)
            ),
        ) {
            Text(
                text = stringResource(id = R.string.get_started),
                fontSize = 20.sp
            )

        }
    }
}