package com.matchloper.data

data class FindParticipantData(
    val data : List<ParticipantInfo>,
    val message : String
)
data class ParticipantInfo(
    val email : String,
    val introduction : String,
    val name : String,
    val phoneNumber : String,
    val skills : List<SkillDto>,
    val userId : Int,
    val userPositions : List<UserPositionDto>
)
