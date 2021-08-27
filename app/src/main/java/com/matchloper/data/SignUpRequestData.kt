package com.matchloper.data

data class SignUpRequestData(
    val addressDto : AddressDto,
    val email : String,
    val introduction : String,
    val name : String,
    val password : String,
    val phoneNumber : String,
    val skillDtoList : List<SkillDto>,
    val userPositionDtoList : List<UserPositionDto>
)

data class AddressDto(
    val city : String,
    val detailed : String
)

data class SkillDto(
    val name : String
)

data class UserPositionDto(
    val type : String
)
