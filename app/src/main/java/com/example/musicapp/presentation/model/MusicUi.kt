package com.example.musicapp.presentation.model

data class MusicUi(
    val avatar: String,
    val description: String,
    val file: String,
    val title: String,
    val id: String,
) {
    fun isUnknown() = this == unknown

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
