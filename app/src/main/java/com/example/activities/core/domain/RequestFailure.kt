package com.example.activities.core.domain

sealed class RequestFailure {
    class ApiError(var code: List<Int> = ArrayList(), var message: String = "") : RequestFailure()
    object NoConnectionError : RequestFailure()
    object UnknownError : RequestFailure()
}