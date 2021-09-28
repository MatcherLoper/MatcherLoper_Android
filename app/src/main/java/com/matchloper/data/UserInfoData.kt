package com.matchloper.data

data class UserInfoData(
    val userId : Int,
    val email : String,
    val password : String,
    val name : String,
    val phoneNumber : String,
    val introduction : String,
    val userPosition : List<UserPositionDto>,
    val skills : List<SkillDto>
)