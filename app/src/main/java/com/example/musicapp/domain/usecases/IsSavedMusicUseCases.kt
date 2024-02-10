package com.example.musicapp.domain.usecases

import kotlinx.coroutines.flow.Flow

interface IsSavedMusicUseCases {

    operator fun invoke(musicId:String): Flow<Boolean>

}