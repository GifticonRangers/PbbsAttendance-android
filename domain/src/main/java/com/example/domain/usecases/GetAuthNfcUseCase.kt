package com.example.domain.usecases

import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.NfcRepository
import javax.inject.Inject

class GetAuthNfcUseCase @Inject constructor(private val nfcRepository: NfcRepository){
    suspend fun invoke(id:Int, dto:LectureInfoDto):String{
        return nfcRepository.authNfcTag(id,dto)
    }
}