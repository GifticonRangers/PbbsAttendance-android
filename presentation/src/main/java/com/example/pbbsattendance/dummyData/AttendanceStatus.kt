package com.example.pbbsattendance.dummyData

data class AttendanceStatus(
    var body: Array<Lecture> = arrayOf(Lecture(1,"출석"),Lecture(2,"출석"),Lecture(3,"출석") )
)

data class Lecture(
    var classOrder: Int,
    var status:String
)