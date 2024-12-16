package com.example.core.domain.usecase

import android.annotation.SuppressLint
import android.content.res.Resources.NotFoundException
import com.example.core.domain.models.TicketModel
import com.example.core.domain.repository.AuthorRepository
import com.example.core.domain.repository.TicketsRepository
import com.example.core.domain.repository.UserRepository
import kotlinx.datetime.LocalDateTime
import java.text.SimpleDateFormat
import java.util.Date

class CreateTicketUseCase(
    private val repository: TicketsRepository,
    private val userRepository: UserRepository,
    private val authorRepository: AuthorRepository
) {

    @SuppressLint("SimpleDateFormat")
    suspend operator fun invoke(
        departmentId: Int,
        type: Int,
        description: String
    ){
//        val sdf = SimpleDateFormat("dd/M/yyyy'T'hh:mm:ss")
        val user = userRepository.getMeUser()
        val author = authorRepository.getAuthorByUserId(user.id)

        repository.createTicket(object: TicketModel{
            override val id: Int?
                get() = null
            override val departmentId: Int
                get() = departmentId
            override val createdAt: Date
                get() = Date()
//                    sdf.format()
//            LocalDateTime.parse(sdf.format(Date()))
            override val completedAt: Date?
                get() = null
            override var status: Int
                get() = 2
                set(value) {}
            override val author: Int
                get() = author.id
            override var executor: Int?
                get() = null
                set(value) {}
            override val type: Int
                get() = type
            override val description: String
                get() = description
        })
    }
}