package com.matchloper.data

data class EntryViewData(
    val entryMatchingUserResponse: EntryMatchingUserResponse,
    val entryNoneUserResponse: EntryNoneUserResponse,
    val entryTotalDataResponse: EntryTotalDataResponse
)
data class EntryMatchingUserResponse(
    val androidMatchingUserCnt : Int,
    val backEndMatchingUserCnt : Int,
    val frontEndMatchingUserCnt : Int,
    val iosMatchingUserCnt : Int
)
data class EntryNoneUserResponse(
    val androidNoneUserCnt : Int,
    val backEndNoneUserCnt : Int,
    val frontEndNoneUserCnt : Int,
    val iosNoneUserCnt : Int
)
data class EntryTotalDataResponse(
    val allUserCnt : Int,
    val openRoomCnt : Int
)

