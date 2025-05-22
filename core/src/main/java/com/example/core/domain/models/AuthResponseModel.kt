package com.example.core.domain.models

interface AuthResponseModel {
    val token: String
    val id_user: Int
    val role: String
}