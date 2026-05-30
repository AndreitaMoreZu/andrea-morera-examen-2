package com.panini.ticketsupport.domain.model

enum class TicketStatus(val label: String) {
    OPEN("Abierto"),
    IN_PROGRESS("En progreso"),
    RESOLVED("Resuelto"),
    CLOSED("Cerrado")
}
