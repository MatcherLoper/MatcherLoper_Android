package com.matchloper.data

data class FindRoomResponseData(
    val data : List<RoomInfoData>,
    val message : String
)
data class RoomInfoData(
    val createUserId : Int,
    val name : String,
    val possibleOfflineArea : String,
    val requiredUserNumber : Int,
    val roomId : Int,
    val roomPositions : List<RoomPosition>,
    val status : String,
    val users : List<UserInfo>
)
data class UserInfo(
    val address : AddressDto,
    val email : String,
    val introduction : String,
    val name : String,
    val phoneNumber : String,
    val skills : List<SkillDto>,
    val userPositions : List<UserPositionDto>
)