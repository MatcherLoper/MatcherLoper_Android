package com.matchloper.data

data class RoomCreateRequestData(
    val name : String,
    val possibleOfflineArea : String,
    val roomPositionList : List<RoomPosition>,
    val userId : Int
)