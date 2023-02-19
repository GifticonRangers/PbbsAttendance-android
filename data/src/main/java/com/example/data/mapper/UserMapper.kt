package com.example.data.mapper

import com.example.data.entity.UserResponse
import com.example.domain.model.User
import com.example.domain.utils.getDayOfSemester
import com.example.domain.utils.getRegisterYear
import com.example.domain.utils.getSemester
import java.time.LocalDate

object UserMapper {
    fun mapperToUser(userResponse: UserResponse): User {
        return User(
            id = userResponse.idUser,
            pw = userResponse.pwUser,
            name = userResponse.nameUser,
            type = userResponse.typeUser,
            gender = "미정",
            department = userResponse.departmentUser,
            registerYear = getRegisterYear(userResponse.idUser),
            email = userResponse.emailUser,
            thisYear = LocalDate.now().year.toString(),
            thisSemester = getSemester(),
            dayOfSemester = getDayOfSemester()
        )
    }
}