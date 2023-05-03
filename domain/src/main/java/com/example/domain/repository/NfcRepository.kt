package com.example.domain.repository

import com.example.domain.model.dto.LectureInfoDto

interface NfcRepository {
    suspend fun startNfcTag(dto:LectureInfoDto):String
    suspend fun endNfcTag(dto:LectureInfoDto):String
}