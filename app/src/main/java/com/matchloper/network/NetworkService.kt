package com.matchloper.network

import com.matchloper.data.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @POST("/api/v1/users/signin")
    fun signIn(
        @Body signInRequest : SignInRequestData
    ) : Call<SignInResponseData>

    @POST("/api/v1/users/signup")
    fun signUp(
        @Body signUpRequest : SignUpRequestData
    ) : Call<SignUpResponseData>

    @POST("/api/v1/room")
    fun createRoom(
        @Body request : RoomCreateRequestData
    ) : Call<DefaultResponseData>

    @GET("/api/v1/entry/")
    fun getEntryView() : Call<EntryViewResponseData>

    @DELETE("/api/v1/rooms/{roomId}")
    fun deleteRoom(
        @Path("roomId") roomId : Int
    ) : Call<DefaultResponseData>

    @GET("/api/v1/rooms")
    fun getRooms() : Call<FindRoomResponseData>

    @GET("/api/v1/rooms/{roomId}")
    fun getRoom(
        @Path ("roomId") roomId: Int
    ) : Call<FindRoomOneResponseData>

    @GET("/api/v1/rooms/open")
    fun getOpenedRoom() : Call<FindRoomResponseData>

    @PUT("/api/v1/rooms/{roomId}/start")
    fun startRoom(
        @Path("roomId") roomId: Int
    ) : Call<DefaultResponseData>

    @PUT("/api/v1/users/{userId}/cancel/matching")
    fun cancelMatching(
        @Path("userId") userId: Int
    ) : Call<DefaultResponseData>

    @POST("/api/v1/users/{userId}/joining/room")
    fun joinRoom(
        @Body request : RequestPositionData,
        @Path ("userId") userId: Int
    ) : Call<DefaultResponseData>

    @PUT("/api/v1/users/{userId}/leave/{roomId}")
    fun leaveRoom(
        @Path("roomId") roomId: Int,
        @Path("userId") userId: Int
    ) : Call<DefaultResponseData>

    @GET("/api/v1/rooms/{roomId}/users/type/participant")
    fun findParticipantJoinedRoom(
        @Path ("roomId") roomId: Int
    ) : Call<FindParticipantData>

    @PUT("/api/v1/rooms/{roomId}/close")
    fun closeRoom(
        @Path ("roomId") roomId : Int
    ) : Call<DefaultResponseData>

    @GET("/api/v1/users/{userId}")
    fun findUser(
        @Path ("userId") userId : Int
    ) : Call<UserInfoResponseData>

    @DELETE("/api/v1/users/{userId}")
    fun deleteUser(
        @Path ("userId") userId: Int
    ) : Call<DefaultResponseData>

    @PUT("/api/v1/users/{userId}")
    fun updateUser(
        @Path ("userId") userId: Int,
        @Body updateRequestBody : UserUpdateRequestData
    ) : Call<DefaultResponseData>
}