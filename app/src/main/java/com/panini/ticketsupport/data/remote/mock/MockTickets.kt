package com.panini.ticketsupport.data.remote.mock

import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.domain.model.Ticket
import com.panini.ticketsupport.domain.model.TicketCategory
import com.panini.ticketsupport.domain.model.TicketStatus

object MockTickets {

    val tickets: List<Ticket> = listOf(
        Ticket(
            id = "TKT-001",
            title = "Faltante crítico de stickers selección española",
            description = "El proveedor Gráficas Especiales no entregó el lote completo de stickers de la selección española (Serie 14). Faltan aproximadamente 85,000 unidades que afectan la distribución en zona Centroamérica.",
            priority = Priority.CRITICAL,
            status = TicketStatus.OPEN,
            category = TicketCategory.INVENTORY,
            provider = "Gráficas Especiales S.A.",
            createdAt = "2026-05-28",
            reportedBy = "Sofía Ramírez"
        ),
        Ticket(
            id = "TKT-002",
            title = "Retraso en entrega de paquetes — zona norte CR",
            description = "LogiTrans CR reporta que el camión de distribución sufrió un desperfecto mecánico en ruta a San Carlos. Los 1,200 cajas de sobres no llegarán a tiempo a los puntos de venta del norte del país.",
            priority = Priority.HIGH,
            status = TicketStatus.IN_PROGRESS,
            category = TicketCategory.DISTRIBUTION,
            provider = "LogiTrans CR",
            createdAt = "2026-05-27",
            reportedBy = "Carlos Mora"
        ),
        Ticket(
            id = "TKT-003",
            title = "Error de impresión — stickers duplicados tanda 3",
            description = "Se detectó que en la tanda 3 del mes de mayo se imprimieron duplicados del sticker número 247 (Kylian Mbappé). Aproximadamente 12,000 sobres contienen el mismo sticker dos veces y ninguno del jugador 248.",
            priority = Priority.HIGH,
            status = TicketStatus.OPEN,
            category = TicketCategory.QUALITY,
            provider = "Impresora Nacional Ltda.",
            createdAt = "2026-05-26",
            reportedBy = "Andrea Torres"
        ),
        Ticket(
            id = "TKT-004",
            title = "Inventario insuficiente — punto de venta central San José",
            description = "El punto de venta central de San José reporta que el stock de sobres estándar (pack x5) es inferior al mínimo requerido para el fin de semana. Se necesitan al menos 500 cajas adicionales antes del sábado.",
            priority = Priority.MEDIUM,
            status = TicketStatus.OPEN,
            category = TicketCategory.INVENTORY,
            provider = "Distribuidora Centroamérica",
            createdAt = "2026-05-29",
            reportedBy = "Luis Campos"
        ),
        Ticket(
            id = "TKT-005",
            title = "Problema logístico en aduana — cargamento Italia",
            description = "El cargamento de 450,000 stickers de edición especial enviado desde Panini SpA en Módena, Italia, fue retenido en aduana costarricense por documentación incompleta. Se requiere gestión urgente con el agente aduanero.",
            priority = Priority.CRITICAL,
            status = TicketStatus.IN_PROGRESS,
            category = TicketCategory.LOGISTICS,
            provider = "Panini SpA Italia",
            createdAt = "2026-05-25",
            reportedBy = "María Jiménez"
        ),
        Ticket(
            id = "TKT-006",
            title = "Proveedor incumple contrato de entrega semanal",
            description = "FIFA Licensed Printers Inc. ha incumplido por segunda semana consecutiva el contrato de entrega de 200,000 stickers de equipos asiáticos. El incumplimiento genera retrasos en la completación del álbum para los clientes.",
            priority = Priority.HIGH,
            status = TicketStatus.RESOLVED,
            category = TicketCategory.PROVIDER,
            provider = "FIFA Licensed Printers Inc.",
            createdAt = "2026-05-20",
            reportedBy = "Pablo Vargas"
        )
    )
}
