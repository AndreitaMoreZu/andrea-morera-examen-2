package com.panini.ticketsupport.domain.model

enum class Priority(val level: Int, val label: String) {
    LOW(1, "Baja"),
    MEDIUM(2, "Media"),
    HIGH(3, "Alta"),
    CRITICAL(4, "Crítica")
}
