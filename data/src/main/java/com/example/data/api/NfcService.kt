package com.example.data.api

import com.example.domain.model.dto.LectureInfoDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NfcService {
    @POST("/api/nfc/startNfcTag")
    suspend fun startNfcTag(@Body dto: LectureInfoDto):ApiResponse<Unit>

    @POST("/api/nfc/endNfcTag")
    suspend fun endNfcTag(@Body dto: LectureInfoDto):ApiResponse<Unit>
}