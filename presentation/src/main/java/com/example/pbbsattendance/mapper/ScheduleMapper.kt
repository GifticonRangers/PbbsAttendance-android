package com.example.pbbsattendance.mapper

import com.example.domain.model.ScheduleSubjectModel
import com.example.pbbsattendance.util.mapScheduleDay
import com.islandparadise14.mintable.ScheduleEntity

object ScheduleMapper {
    val INF = 1000000
    fun mapperToSchedule(scheduleSubjectModel: ScheduleSubjectModel):ScheduleEntity{
        return ScheduleEntity(
            originId = scheduleSubjectModel.id?:INF,
            startTime = scheduleSubjectModel.startTimeSubject?:"",
            endTime = scheduleSubjectModel.endTimeSubject?:"",
            scheduleDay = mapScheduleDay(scheduleSubjectModel.daySubject),
            roomInfo = scheduleSubjectModel.locationSubject?:"",
            scheduleName = scheduleSubjectModel.nameSubject?:"",
        )
    }
}