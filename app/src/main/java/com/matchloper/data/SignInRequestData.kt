package com.matchloper.data

data class SignInRequestData(
    val deviceToken : String,
    val email : String,
    val password : String
)
