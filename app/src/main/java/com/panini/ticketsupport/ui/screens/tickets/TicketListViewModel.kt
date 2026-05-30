package com.panini.ticketsupport.ui.screens.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.panini.ticketsupport.data.repository.TicketRepository
import com.panini.ticketsupport.domain.AppContainer
import com.panini.ticketsupport.domain.featureflags.FeatureFlagsManager
import com.panini.ticketsupport.domain.model.Ticket
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TicketListViewModel(private val ticketRepository: TicketRepository) : ViewModel() {

    // Tickets sorted by priority descending — reacts automatically to any change in the repository
    val tickets: StateFlow<List<Ticket>> = ticketRepository.tickets
        .map { list -> list.sortedByDescending { it.priority.level } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // Feature flag — FAB visibility reacts automatically when flag changes
    val canCreateTickets: StateFlow<Boolean> = FeatureFlagsManager.canCreateTickets

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { TicketListViewModel(AppContainer.ticketRepository) }
        }
    }
}
