package com.example.hs_test.data

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T?, message: String) : Resource<T>(data, message)
}

fun <T> handleSuccess(data: T): Resource<T> = Resource.Success(data)

fun <T> handleException(e: Exception): Resource<T> =
    Resource.Error(message = e.message ?: "Empty error message", data = null)

