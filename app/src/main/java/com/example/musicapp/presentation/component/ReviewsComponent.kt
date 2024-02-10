package com.example.musicapp.presentation.component

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.models.ReviewsDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ReviewersItemList(
    reviewsFlow: StateFlow<List<ReviewsDomain>>,
    modifier: Modifier = Modifier
) {
    val reviews = reviewsFlow.collectAsStateWithLifecycle().value
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = reviews
        ) { item ->
            ReviewsItem(reviews = item)
        }
    }
}

@Composable
fun ReviewsItem(
    reviews: ReviewsDomain,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = reviews.reviewsDetails.avatarPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profile_default_avatar),
                error = painterResource(id = R.drawable.profile_default_avatar),
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = reviews.reviewsDetails.name,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = reviews.description,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@SuppressLint("NewApi")
@Preview
@Composable
fun ReviewersItemListPreview() {
    MaterialTheme {
        ReviewersItemList(
            reviewsFlow = MutableStateFlow(
                listOf(
                    ReviewsDomain.unknown,
                )
            ),
            modifier = Modifier.background(MaterialTheme.colors.background)
        )
    }
}