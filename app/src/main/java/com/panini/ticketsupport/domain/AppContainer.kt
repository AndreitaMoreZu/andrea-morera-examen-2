package com.panini.ticketsupport.domain

import com.panini.ticketsupport.data.remote.RetrofitClient
import com.panini.ticketsupport.data.repository.AuthRepository
import com.panini.ticketsupport.data.repository.TicketRepository

object AppContainer {
    private val apiService = RetrofitClient.apiService
    val authRepository: AuthRepository by lazy { AuthRepository(apiService) }
    val ticketRepository: TicketRepository by lazy { TicketRepository(apiService) }
}
