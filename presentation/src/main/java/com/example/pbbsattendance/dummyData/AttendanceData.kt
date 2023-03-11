package com.example.pbbsattendance.dummyData

data class AttendanceStatus(
    var body: Array<Lecture> = arrayOf(Lecture(1,"출석"),Lecture(2,"지각"),Lecture(3,"결석"),Lecture(4,"공결") )
)
data class AttendanceHistory(
    var body: Array<Attendance> = arrayOf(
        Attendance(2, 3, "출석", "23-93-08"),
        Attendance(2, 2, "출석", "23-93-08"),
        Attendance(2, 1, "출석", "23-93-08"),
        Attendance(1, 3, "출석", "23-93-08"),
        Attendance(1, 2, "출석", "23-93-08"),
        Attendance(1, 1, "출석", "23-93-08"),
    )
)

data class Lecture(
    var classOrder: Int,
    var status:String
)

data class Attendance(
    var classWeek: Int,
    var classOrder: Int,
    var status: String,
    var date: String
)