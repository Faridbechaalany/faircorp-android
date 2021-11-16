package com.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {
    @GET("rooms/buildings/{buildingid}")
    fun findRoomsByBuildingId(@Path("buildingid")id: Long ): Call<List<RoomDto>>
}