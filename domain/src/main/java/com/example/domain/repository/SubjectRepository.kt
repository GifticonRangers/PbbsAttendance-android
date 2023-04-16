package com.example.domain.repository

import com.example.domain.model.ScheduleSubjectModel
import com.example.domain.model.dto.IdDto

interface SubjectRepository {
    suspend fun showSubjects(id:IdDto):ArrayList<ScheduleSubjectModel>
}