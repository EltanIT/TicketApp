package com.example.core.data.model

import com.example.core.domain.models.TicketModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
class TicketModelImpl(
    @SerializedName("iD_Ticket") override val id: Int?,
    @SerializedName("iD_Department") override val departmentId: Int,
    @SerializedName("dateOfCreation") @Contextual override val createdAt: Date,
    @SerializedName("dateOfCompletion") @Contextual override val completedAt: Date?,
    override var status: Int,
    override val author: Int,
    override var executor: Int?,
    override val type: Int,
    override val description: String
) : TicketModel {

    companion object{
        fun TicketModel.toBody() = TicketModelImpl(
            id,
            departmentId,
            createdAt,
            completedAt,
            status,
            author,
            executor,
            type,
            description
        )
    }
}