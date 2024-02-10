package com.example.musicapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MusicHorizontalItem(
    title: String,
    musicId: String,
    posterUrl: String,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 9.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .size(58.dp)
                .clip(CircleShape),
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.width(20.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = modifier.width(20.dp))
    }
}