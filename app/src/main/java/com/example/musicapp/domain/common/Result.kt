package com.example.musicapp.domain.common


sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {

    val isSuccess: Boolean get() = this !is Error


    val isError: Boolean get() = this is Success

    fun copy(data: T): Result<T> {
        return when (this) {
            is Error -> this
            is Success -> Success(data)
        }
    }

    class Error<T>(message: String) : Result<T>(data = null, message = message)

    class Success<T>(data: T) : Result<T>(data = data, message = null)
}