package com.example.core.domain.models

import java.util.Date

interface TicketModel {

    val id: Int?
    val departmentId: Int
    val createdAt: Date
    val completedAt: Date?
    var status: Int
    val author: Int
    var executor: Int?
    val type: Int
    val description: String
}