package com.panini.ticketsupport.ui.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.panini.ticketsupport.core.UserMessages
import com.panini.ticketsupport.data.repository.TicketRepository
import com.panini.ticketsupport.domain.AppContainer
import com.panini.ticketsupport.domain.AuthSession
import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.domain.model.Ticket
import com.panini.ticketsupport.domain.model.TicketCategory
import com.panini.ticketsupport.domain.model.TicketStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class CreateTicketUiState(
    val title: String = "",
    val description: String = "",
    val provider: String = "",
    val priority: Priority = Priority.MEDIUM,
    val category: TicketCategory = TicketCategory.INVENTORY,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class CreateTicketViewModel(private val ticketRepository: TicketRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTicketUiState())
    val uiState: StateFlow<CreateTicketUiState> = _uiState.asStateFlow()

    fun onTitleChange(value: String) { _uiState.value = _uiState.value.copy(title = value, errorMessage = null) }
    fun onDescriptionChange(value: String) { _uiState.value = _uiState.value.copy(description = value) }
    fun onProviderChange(value: String) { _uiState.value = _uiState.value.copy(provider = value, errorMessage = null) }
    fun onPriorityChange(priority: Priority) { _uiState.value = _uiState.value.copy(priority = priority) }
    fun onCategoryChange(category: TicketCategory) { _uiState.value = _uiState.value.copy(category = category) }

    fun createTicket() {
        val state = _uiState.value
        if (state.title.isBlank() || state.provider.isBlank()) {
            _uiState.value = state.copy(errorMessage = UserMessages.Ticket.REQUIRED_FIELDS)
            return
        }
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val ticket = Ticket(
            id = "TKT-${UUID.randomUUID().toString().takeLast(6).uppercase()}",
            title = state.title,
            description = state.description,
            priority = state.priority,
            status = TicketStatus.OPEN,
            category = state.category,
            provider = state.provider,
            createdAt = today,
            reportedBy = AuthSession.currentUser?.name ?: "Usuario"
        )
        ticketRepository.createTicket(ticket)
        _uiState.value = state.copy(isSuccess = true)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { CreateTicketViewModel(AppContainer.ticketRepository) }
        }
    }
}
