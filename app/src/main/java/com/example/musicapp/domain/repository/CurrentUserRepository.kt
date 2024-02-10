package com.example.musicapp.domain.repository

interface CurrentUserRepository {

    fun isOnBoardingPassed(): Boolean

    fun setOnBoardingShowed()

    fun clearOnBoarding()
}