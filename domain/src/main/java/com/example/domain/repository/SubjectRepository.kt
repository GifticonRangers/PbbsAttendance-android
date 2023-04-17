package com.example.domain.repository

import com.example.domain.model.ScheduleSubjectModel
import com.example.domain.model.dto.IdDto

interface SubjectRepository {
    suspend fun showScheduleSubjects(id: IdDto):ArrayList<ScheduleSubjectModel>
}