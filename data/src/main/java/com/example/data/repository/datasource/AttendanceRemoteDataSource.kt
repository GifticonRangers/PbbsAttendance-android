package com.example.data.repository.datasource

import com.example.data.api.AttendanceService
import com.example.data.mapper.AttendanceMapper
import com.example.data.mapper.UserMapper
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.UserBriefModel
import com.example.domain.model.dto.IdAttendanceStateDto
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.type.AttendanceState
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AttendanceRemoteDataSource {
    suspend fun showLiveAttendanceTotalInfo(dto: LectureInfoDto):Flow<AttendanceTotalModel>
    suspend fun showAttendanceTotalInfo(dto: LectureInfoDto):AttendanceTotalModel
    suspend fun showLiveAttendanceHistory(dto: StudentSubjectDto):Flow<ArrayList<AttendanceHistoryModel>>
    suspend fun showHoldAttendance(dto:LectureInfoDto):ArrayList<UserBriefModel>
    suspend fun updateAttendance(dto: IdAttendanceStateDto):AttendanceHistoryModel
}

class AttendanceRemoteDataSourceImpl @Inject constructor(private val api: AttendanceService):AttendanceRemoteDataSource {
    override suspend fun showLiveAttendanceTotalInfo(dto: LectureInfoDto): Flow<AttendanceTotalModel> {
        val refreshIntervalMs:Long = 3000
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

    override suspend fun showLiveAttendanceHistory(dto: StudentSubjectDto): Flow<ArrayList<AttendanceHistoryModel>> {
        val refreshIntervalMs:Long = 3000
        var result = ArrayList<AttendanceHistoryModel>()
        val attendanceHistory: Flow<ArrayList<AttendanceHistoryModel>> = flow {
            while (true){
                api.showAttendanceByUser(dto).suspendOnSuccess {
                    this.data.forEach {
                        val value = AttendanceMapper.mapToAttendanceHistoryModelMapper(it)
                        result.add(value)
                    }
                }
                emit(result)
                delay(refreshIntervalMs)
            }
        }
        return attendanceHistory
    }

    override suspend fun showHoldAttendance(dto: LectureInfoDto): ArrayList<UserBriefModel> {
        val result = arrayListOf<UserBriefModel>()
        api.showHoldAttendance(dto).suspendOnSuccess {
            this.data.forEach {
                result.add(UserMapper.mapToUserBriefModel(it))
            }
        }
        return result
    }

    override suspend fun updateAttendance(dto: IdAttendanceStateDto): AttendanceHistoryModel {
        var result = AttendanceHistoryModel("","","",AttendanceState.NULL,0,0,0)
        api.updateAttendance(dto).suspendOnSuccess {
            result = AttendanceMapper.mapToAttendanceHistoryModelMapper(this.data)
        }
        return result
    }
}