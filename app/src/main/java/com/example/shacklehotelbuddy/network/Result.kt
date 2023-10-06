package com.example.shacklehotelbuddy.network

sealed class Result<out T> {
    data class Success<out T>(val content: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
}