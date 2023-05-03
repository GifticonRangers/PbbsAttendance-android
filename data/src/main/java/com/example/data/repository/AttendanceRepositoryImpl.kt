package com.example.data.repository

import com.example.data.api.AttendanceService
import com.example.data.mapper.AttendanceMapper
import com.example.data.repository.datasource.AttendanceRemoteDataSource
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.repository.AttendanceRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(private val remoteDataSource: AttendanceRemoteDataSource, private val api:AttendanceService):AttendanceRepository {
    override suspend fun showAttendanceTimeList(dto: UserSubjectDto): ArrayList<LectureDateModel> {
        val result = arrayListOf<LectureDateModel>()
        api.showAttendanceTimeList(dto).suspendOnSuccess {
            this.data.forEach{
                result.add(AttendanceMapper.mapperToAttendanceDateMapper(it))
            }
        }
        return result
    }

    override suspend fun showLiveAttendanceTotalInfo(dto: LectureInfoDto): Flow<AttendanceTotalModel> {
        return remoteDataSource.showLiveAttendanceTotalInfo(dto)
    }

    override suspend fun showAttendanceTotalInfo(dto: LectureInfoDto): AttendanceTotalModel {
        return remoteDataSource.showAttendanceTotalInfo(dto)
    }

    override suspend fun showAttendanceHistory(dto: StudentSubjectDto): ArrayList<AttendanceHistoryModel> {
        var result = arrayListOf<AttendanceHistoryModel>()
        api.showAttendanceByUser(dto).suspendOnSuccess {
            this.data.forEach {
                val value =  AttendanceMapper.mapToAttendanceHistoryModelMapper(it)
                result.add(value)
            }
        }
        return result
    }

    override suspend fun showAttendanceByTime(dto: LectureInfoDto): ArrayList<AttendanceHistoryModel> {
        var result = arrayListOf<AttendanceHistoryModel>()
        api.showAttendanceByTime(dto).suspendOnSuccess {
            this.data.forEach {
                val value = AttendanceMapper.mapToAttendanceHistoryModelMapper(it)
                result.add(value)
            }
        }
        return result
    }
}