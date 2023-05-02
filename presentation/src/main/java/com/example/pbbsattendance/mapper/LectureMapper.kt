package com.example.pbbsattendance.mapper

import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.pbbsattendance.model.LectureTimeItemModel

object LectureMapper {
    fun mapperToLectureDate(lectureDateModel: LectureDateModel):LectureTimeItemModel{
        return LectureTimeItemModel(
            time = lectureDateModel.time.toString(),
            date = lectureDateModel.year + "/" + lectureDateModel.month + "/" + lectureDateModel.day,
            week = lectureDateModel.week?:""
        )
    }

    fun mapToLectureInfoDto(lectureTimeItemModel: LectureTimeItemModel, idSubject:Int):LectureInfoDto{
        return LectureInfoDto(
            timeAttendance = lectureTimeItemModel.time + "차시",
            weekAttendance = lectureTimeItemModel.week + "주차",
            idSubject = idSubject.toString()
        )
    }
}