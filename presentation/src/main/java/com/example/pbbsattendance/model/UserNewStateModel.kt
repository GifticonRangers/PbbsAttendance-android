package com.example.pbbsattendance.model

import com.example.domain.model.UserBriefModel

data class UserNewStateModel(
    val userBriefModel:UserBriefModel,
    val state:Int
)
