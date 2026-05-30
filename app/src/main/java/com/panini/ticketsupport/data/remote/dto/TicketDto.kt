package com.panini.ticketsupport.data.remote.dto

data class TicketDto(
    val id: String,
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val category: String,
    val provider: String,
    val createdAt: String,
    val reportedBy: String
)
