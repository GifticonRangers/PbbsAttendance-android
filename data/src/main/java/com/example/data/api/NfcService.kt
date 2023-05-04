package com.example.data.api

import com.example.domain.model.dto.LectureInfoDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface NfcService {
    @POST("/api/nfc/startNfcTag")
    suspend fun startNfcTag(@Body dto: LectureInfoDto):ApiResponse<Unit>

    @POST("/api/nfc/endNfcTag")
    suspend fun endNfcTag(@Body dto: LectureInfoDto):ApiResponse<Unit>

    @POST("/api/nfc/authNFC/{id}")
    suspend fun authNfcTag(
        @Path("id") id:Int,
        @Body dto:LectureInfoDto
    ):ApiResponse<Unit>
}