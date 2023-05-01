package com.example.data.repository

import com.example.data.api.AttendanceService
import com.example.data.mapper.AttendanceMapper
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.repository.AttendanceRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(private val api:AttendanceService):AttendanceRepository {
    override suspend fun showAttendanceTimeList(dto: UserSubjectDto): ArrayList<LectureDateModel> {
        val result = arrayListOf<LectureDateModel>()
        api.showAttendanceTimeList(dto).suspendOnSuccess {
            this.data.forEach{
                result.add(AttendanceMapper.mapperToAttendanceDateMapper(it))
            }
        }
        return result
    }

    override suspend fun showAttendanceTotalInfo(dto: LectureInfoDto): AttendanceTotalModel {
        var result = AttendanceTotalModel(0,0,0,0)
        api.showAttendanceInfo(dto).suspendOnSuccess {
            result = AttendanceMapper.mapToAttendanceTotalMapper(this.data)
        }
        return result
    }

    override suspend fun showAttendanceHistory(dto: UserSubjectDto): ArrayList<AttendanceHistoryModel> {
        var result = arrayListOf<AttendanceHistoryModel>()
        api.showAttendanceByUser(dto).suspendOnSuccess {
            this.data.forEach {
                val value =  AttendanceMapper.mapToAttendanceHistoryModelMapper(it)
                result.add(value)
            }
        }
        return result
    }
}