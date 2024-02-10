package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.repository.CurrentUserRepository

interface ShouldOnBoardingPassedUseCases {


    operator fun invoke(): Boolean
}

class ShouldOnBoardingPassedUseCasesImpl(
    private val repository: CurrentUserRepository
) : ShouldOnBoardingPassedUseCases {

    override fun invoke(): Boolean {
        return repository.isOnBoardingPassed()
    }

}