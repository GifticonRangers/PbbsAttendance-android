package com.example.domain.repository

import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendantModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.LectureInfoDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser():UserModel
    suspend fun getUserBySubjectId(dto: IdDto):ArrayList<UserModel>
    suspend fun getAttendantBySubjectId(dto: IdDto):ArrayList<AttendantModel>
    suspend fun showUserAttendanceBySubjectId(dto: LectureInfoDto): Flow<ArrayList<AttendantModel>>
}