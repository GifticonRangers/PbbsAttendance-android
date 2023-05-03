package com.example.domain.usecases

import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.NfcRepository
import javax.inject.Inject

class EndNfcTagUseCase @Inject constructor(private val nfcRepository: NfcRepository){
    suspend fun invoke(dto:LectureInfoDto):String{
        val result = nfcRepository.endNfcTag(dto)
        return result
    }
}