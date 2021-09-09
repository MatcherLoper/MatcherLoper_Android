package com.matchloper.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserInfoData(
    val userId : Int,
    val email : String,
    val password : String,
    val name : String,
    val phoneNumber : String,
    val introduction : String,
    val userPosition : @RawValue List<UserPositionDto>,
    val skills : @RawValue List<SkillDto>
) : Parcelable
