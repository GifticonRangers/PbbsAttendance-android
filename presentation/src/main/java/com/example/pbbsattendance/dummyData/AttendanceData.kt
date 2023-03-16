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
data class StudentList(
    var body: Array<Student> = arrayOf(
        Student("김문기", "202001520", true),
        Student("김진", "202001530", true),
        Student("장희권", "202001540", false),
        Student("이용인", "202001550", true),
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

data class Student(
    var name: String,
    var studentId: String,
    var isAttendance: Boolean
)