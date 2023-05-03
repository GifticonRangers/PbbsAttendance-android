package com.example.data.repository.datasource

import com.example.data.api.AttendanceService
import com.example.data.mapper.AttendanceMapper
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.dto.LectureInfoDto
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AttendanceRemoteDataSource {
    suspend fun showLiveAttendanceTotalInfo(dto: LectureInfoDto):Flow<AttendanceTotalModel>
    suspend fun showAttendanceTotalInfo(dto: LectureInfoDto):AttendanceTotalModel
}

class AttendanceRemoteDataSourceImpl @Inject constructor(private val api: AttendanceService):AttendanceRemoteDataSource {
    override suspend fun showLiveAttendanceTotalInfo(dto: LectureInfoDto): Flow<AttendanceTotalModel> {
        val refreshIntervalMs:Long = 10000
        var result = AttendanceTotalModel(0,0,0,0)
        val attendanceTotalInfo: Flow<AttendanceTotalModel> = flow {
            while (true){
                api.showAttendanceInfo(dto).suspendOnSuccess {
                    result = AttendanceMapper.mapToAttendanceTotalMapper(this.data)

                }
                emit(result)
                delay(refreshIntervalMs)
            }
        }
        return attendanceTotalInfo
    }

    override suspend fun showAttendanceTotalInfo(dto: LectureInfoDto): AttendanceTotalModel {
        var result = AttendanceTotalModel(0,0,0,0)
        api.showAttendanceInfo(dto).suspendOnSuccess {
            result = AttendanceMapper.mapToAttendanceTotalMapper(this.data)
        }
        return result
    }
}