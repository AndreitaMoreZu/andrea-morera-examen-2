package com.panini.ticketsupport.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.panini.ticketsupport.data.repository.TicketRepository
import com.panini.ticketsupport.domain.AppContainer
import com.panini.ticketsupport.domain.featureflags.FeatureFlagsManager
import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.domain.model.Ticket
import com.panini.ticketsupport.domain.model.TicketStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class TicketDetailUiState(
    val successMessage: String? = null,
    val errorMessage: String? = null
)

class TicketDetailViewModel(
    private val ticketRepository: TicketRepository,
    private val ticketId: String
) : ViewModel() {

    // Ticket reacts automatically when repository emits a new list
    val ticket: StateFlow<Ticket?> = ticketRepository.tickets
        .map { list -> list.find { it.id == ticketId } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ticketRepository.getById(ticketId)
        )

    val canUpdatePriority: StateFlow<Boolean> = FeatureFlagsManager.canUpdatePriority

    private val _uiState = MutableStateFlow(TicketDetailUiState())
    val uiState: StateFlow<TicketDetailUiState> = _uiState.asStateFlow()

    fun updateStatus(status: TicketStatus) {
        ticketRepository.updateStatus(ticketId, status)
        _uiState.value = _uiState.value.copy(successMessage = "Estado actualizado correctamente")
    }

    fun updatePriority(priority: Priority) {
        ticketRepository.updatePriority(ticketId, priority)
        _uiState.value = _uiState.value.copy(successMessage = "Prioridad actualizada correctamente")
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(successMessage = null, errorMessage = null)
    }

    companion object {
        fun factory(ticketId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer { TicketDetailViewModel(AppContainer.ticketRepository, ticketId) }
        }
    }
}
