package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.models.XchoDomain
import javax.inject.Inject

interface XchoUseCases{
    suspend operator fun invoke():List<XchoDomain>

}