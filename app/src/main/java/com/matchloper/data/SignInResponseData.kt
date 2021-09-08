package com.matchloper.data

data class SignInResponseData(
    val data : LoginData?,
    val message : String?
)
data class LoginData(
    val authenticationToken : String,
    val id : Int
)
