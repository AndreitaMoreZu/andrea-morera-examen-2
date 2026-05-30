package com.panini.ticketsupport.domain.model

enum class TicketCategory(val label: String) {
    INVENTORY("Inventario"),
    DISTRIBUTION("Distribución"),
    LOGISTICS("Logística"),
    PROVIDER("Proveedor"),
    QUALITY("Calidad")
}
