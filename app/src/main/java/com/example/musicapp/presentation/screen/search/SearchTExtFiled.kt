package com.example.musicapp.presentation.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun SearchTextFieldPreview() {
    MaterialTheme {
        SearchTextField(value = "Search", onValueChange = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray,
            disabledIndicatorColor = Gray,
            unfocusedIndicatorColor = Gray,
            disabledTrailingIconColor =Gray,
        ),
        enabled = isEnabled,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription =null
            )
        }
    )

}