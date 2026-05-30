package com.panini.ticketsupport.navigation

object AppDestinations {
    const val LOGIN = "login"
    const val TICKET_LIST = "ticketList"
    const val TICKET_DETAIL = "ticketDetail/{ticketId}"
    const val CREATE_TICKET = "createTicket"
    const val FEATURE_FLAGS = "featureFlags"

    fun ticketDetailRoute(ticketId: String) = "ticketDetail/$ticketId"
}
