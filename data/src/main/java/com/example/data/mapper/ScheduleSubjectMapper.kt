package com.example.data.mapper

import com.example.data.dto.ScheduleSubjectResponseDto
import com.example.data.util.getHour
import com.example.data.util.getMinute
import com.example.domain.model.ScheduleSubjectModel

object ScheduleSubjectMapper {
    fun mapperToScheduleSubject(scheduleSubjectResponse: ScheduleSubjectResponseDto): ScheduleSubjectModel{
        return ScheduleSubjectModel(
            id = scheduleSubjectResponse.id,
            startHourSubject = getHour(scheduleSubjectResponse.startTimeSubject),
            startMinuteSubject = getMinute(scheduleSubjectResponse.startTimeSubject),
            endHourSubject = getHour(scheduleSubjectResponse.endTimeSubject),
            endMinuteSubject = getMinute(scheduleSubjectResponse.endTimeSubject),
            daySubject = scheduleSubjectResponse.daySubject,
            nameSubject = scheduleSubjectResponse.nameSubject,
            locationSubject = scheduleSubjectResponse.locationSubject,
            profSubject = scheduleSubjectResponse.profSubject
        )
    }
}