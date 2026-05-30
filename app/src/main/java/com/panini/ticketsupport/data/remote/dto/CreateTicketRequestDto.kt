package com.panini.ticketsupport.data.remote.dto

data class CreateTicketRequestDto(
    val title: String,
    val description: String,
    val priority: String,
    val category: String,
    val provider: String,
    val reportedBy: String
)
