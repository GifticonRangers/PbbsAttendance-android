package com.example.pbbsattendance.mapper

import com.example.domain.model.LectureDateModel
import com.example.pbbsattendance.model.LectureTimeItemModel

object LectureMapper {
    fun mapperToLectureDate(lectureDateModel: LectureDateModel):LectureTimeItemModel{
        return LectureTimeItemModel(
            time = lectureDateModel.time.toString(),
            date = lectureDateModel.year + "/" + lectureDateModel.month + "/" + lectureDateModel.day
        )
    }
}