package com.example.musicapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.musicapp.domain.models.DailyDomain

@Composable
fun Recently(
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
    music: DailyDomain,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 9.dp)
            .fillMaxWidth()
            .height(153.dp)
            .clickable {
                navigateToDetails(music.objectId)
            }
            .clip(RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = music.avatar.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    )
                ),
        )
        Spacer(modifier = modifier.width(12.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = music.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(
                text = music.description,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}