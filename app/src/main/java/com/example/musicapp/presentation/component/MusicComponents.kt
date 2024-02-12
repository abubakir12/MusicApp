package com.example.musicapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicapp.domain.models.DailyDomain


@Composable
fun MusicComponents(
    navigateToDetails: (String) -> Unit,
    music: DailyDomain,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(139.dp)
            .width(139.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navigateToDetails(music.objectId)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = music.avatar.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            modifier = Modifier,
            text = music.title,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}