package com.matchloper.data

data class UserUpdateRequestData(
    val addressDto : AddressDto,
    val introduction : String,
    val name : String,
    val password : String,
    val phoneNumber : String,
    val skillDtoList : List<SkillDto>,
    val userPositionDtoList : List<UserPositionDto>
)
