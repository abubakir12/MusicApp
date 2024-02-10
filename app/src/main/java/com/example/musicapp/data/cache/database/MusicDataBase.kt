package com.example.musicapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.musicapp.data.cache.MusicDao
import com.example.musicapp.data.cloud.daily_mix.Image
import com.example.musicapp.data.cloud.daily_mix.MusicFile
import com.example.musicapp.data.models.MusicDailyCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Database(entities = [MusicDailyCache::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class MusicDataBase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}

object Converters {
    @TypeConverter
    fun fromStringToList(value: String?): List<String?>? =
        Gson().fromJson(value, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun fromListToString(list: List<String?>?): String = Gson().toJson(list)

    @TypeConverter
    fun fromFileToString(musicFile: MusicFile?): String = Gson().toJson(musicFile)

    @TypeConverter
    fun fromStringToFile(value: String?): MusicFile? =
        Gson().fromJson(value, object : TypeToken<MusicFile?>() {}.type)

    @TypeConverter
    fun fromStringToAvatar(value: String?): Image? =
        Gson().fromJson(value, object : TypeToken<Image?>() {}.type)

    @TypeConverter
    fun fromAvatarToString(image: Image?): String = Gson().toJson(image)
}
