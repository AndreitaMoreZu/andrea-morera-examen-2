package com.panini.ticketsupport.data.repository

import com.panini.ticketsupport.data.remote.ApiService
import com.panini.ticketsupport.data.remote.mock.MockTickets
import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.domain.model.Ticket
import com.panini.ticketsupport.domain.model.TicketStatus
import com.panini.ticketsupport.shared.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicketRepository(private val apiService: ApiService) {

    // Single source of truth — all screens collect from here
    private val _tickets = MutableStateFlow(MockTickets.tickets)
    val tickets: StateFlow<List<Ticket>> = _tickets.asStateFlow()

    fun createTicket(ticket: Ticket) {
        _tickets.value = listOf(ticket) + _tickets.value
    }

    fun updateStatus(ticketId: String, status: TicketStatus): ApiResult<Unit> {
        val updated = _tickets.value.map {
            if (it.id == ticketId) it.copy(status = status) else it
        }
        _tickets.value = updated
        return ApiResult.Success(Unit)
    }

    fun updatePriority(ticketId: String, priority: Priority): ApiResult<Unit> {
        val updated = _tickets.value.map {
            if (it.id == ticketId) it.copy(priority = priority) else it
        }
        _tickets.value = updated
        return ApiResult.Success(Unit)
    }

    fun getById(ticketId: String): Ticket? = _tickets.value.find { it.id == ticketId }
}
