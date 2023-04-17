package com.example.pbbsattendance.mapper

import com.example.domain.model.ScheduleSubjectModel
import com.example.pbbsattendance.util.mapScheduleDay
import com.github.tlaabs.timetableview.Schedule
import com.github.tlaabs.timetableview.Time

object ScheduleMapper {
    fun mapperToSchedule(scheduleSubjectModel: ScheduleSubjectModel):Schedule{
        val schedule = Schedule()
        schedule.startTime = Time(scheduleSubjectModel.startHourSubject!!, scheduleSubjectModel.startMinuteSubject!!)
        schedule.endTime = Time(scheduleSubjectModel.endHourSubject!!, scheduleSubjectModel.endMinuteSubject!!)
        schedule.day = mapScheduleDay(scheduleSubjectModel.daySubject!!)
        schedule.classPlace = scheduleSubjectModel.locationSubject
        schedule.classTitle = scheduleSubjectModel.nameSubject
        schedule.professorName = scheduleSubjectModel.profSubject

        return schedule
    }
}