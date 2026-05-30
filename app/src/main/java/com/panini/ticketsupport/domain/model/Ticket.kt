package com.panini.ticketsupport.domain.model

data class Ticket(
    val id: String,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TicketStatus,
    val category: TicketCategory,
    val provider: String,
    val createdAt: String,
    val reportedBy: String
)
