package com.example.musicapp.presentation.model

data class MusicUi(
    val avatar: String,
    val description: String,
    val file: String,
    val title: String,
    var isFavorite: Boolean = false,
    val id: String,
) {
    fun doesMatchSearchQuery(query: String): Boolean = listOf(
        "$title$description",
        "$title $description",
        "${title.first()}${avatar.first()}",
        "${title.first()} ${avatar.first()}",
    ).any { it.contains(query, ignoreCase = true) }

    companion object {
        val unknown = MusicUi(
            avatar = "",
            description = "",
            file = "",
            title = "error",
            id = String()
        )
    }
}
