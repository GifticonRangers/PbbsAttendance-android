package com.example.data.repository

import com.example.data.api.SubjectService
import com.example.data.mapper.ScheduleSubjectMapper
import com.example.domain.model.ScheduleSubjectModel
import com.example.domain.model.dto.IdDto
import com.example.domain.repository.SubjectRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(private val api:SubjectService):SubjectRepository{
    override suspend fun showSubjects(dto: IdDto): ArrayList<ScheduleSubjectModel> {
        val result:ArrayList<ScheduleSubjectModel> = arrayListOf()
        api.showScheduleSubjects(dto).suspendOnSuccess {
            this.data.forEach {
                val value = ScheduleSubjectMapper.mapperToScheduleSubject(it)
                result.add(value)
            }
        }
        return result
    }
}