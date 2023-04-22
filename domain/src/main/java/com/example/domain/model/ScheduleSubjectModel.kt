package com.example.domain.model


data class ScheduleSubjectModel(
    val id:Int?,
    val startHourSubject: Int?,
    val startMinuteSubject: Int?,
    val endHourSubject: Int?,
    val endMinuteSubject: Int?,
    val daySubject: String?,
    val nameSubject: String?,
    val locationSubject: String?,
    val profSubject: String?,
)