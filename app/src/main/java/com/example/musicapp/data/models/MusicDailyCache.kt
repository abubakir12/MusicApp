package com.example.musicapp.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.data.cloud.daily_mix.Image
import com.example.musicapp.data.cloud.daily_mix.MusicFile
import com.google.gson.annotations.SerializedName


@Entity(tableName = "music_table")
data class MusicDailyCache(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("avatar")
    val image: Image,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val musicFile: MusicFile,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)