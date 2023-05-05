package com.example.data.mapper

import com.example.data.dto.ScheduleSubjectResponseDto
import com.example.domain.model.ScheduleSubjectModel

object ScheduleSubjectMapper {
    fun mapperToScheduleSubject(scheduleSubjectResponse: ScheduleSubjectResponseDto): ScheduleSubjectModel{
        return ScheduleSubjectModel(
            id = scheduleSubjectResponse.id,
            startTimeSubject = scheduleSubjectResponse.startTimeSubject,
            endTimeSubject = scheduleSubjectResponse.endTimeSubject,
            daySubject = scheduleSubjectResponse.daySubject,
            nameSubject = scheduleSubjectResponse.nameSubject,
            locationSubject = scheduleSubjectResponse.locationSubject,
            profSubject = scheduleSubjectResponse.profSubject
        )
    }
}