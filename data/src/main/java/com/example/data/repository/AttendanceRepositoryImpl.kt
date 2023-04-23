package com.example.data.repository

import com.example.data.api.AttendanceService
import com.example.data.mapper.AttendanceMapper
import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.repository.AttendanceRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(private val api:AttendanceService):AttendanceRepository {
    override suspend fun showAttendanceTimeList(dto: UserSubjectDto): ArrayList<LectureDateModel> {
        val result = arrayListOf<LectureDateModel>()
        api.showAttendanceInfo(dto).suspendOnSuccess {
            this.data.forEach{
                result.add(AttendanceMapper.mapperToAttendanceDateMapper(it))
            }
        }
        return result
    }
}