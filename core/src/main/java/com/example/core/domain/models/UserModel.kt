package com.example.core.domain.models

interface UserModel {
    val id: Int
    val login: String
    val email: String
    val role: String
    val fio: String
    val phoneNumber: String
}