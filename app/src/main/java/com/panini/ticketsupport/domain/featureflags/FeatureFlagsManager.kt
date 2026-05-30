package com.panini.ticketsupport.domain.featureflags

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object FeatureFlagsManager {

    private val _canCreateTickets = MutableStateFlow(true)
    val canCreateTickets: StateFlow<Boolean> = _canCreateTickets.asStateFlow()

    private val _canUpdatePriority = MutableStateFlow(true)
    val canUpdatePriority: StateFlow<Boolean> = _canUpdatePriority.asStateFlow()

    fun setCanCreateTickets(enabled: Boolean) {
        _canCreateTickets.value = enabled
    }

    fun setCanUpdatePriority(enabled: Boolean) {
        _canUpdatePriority.value = enabled
    }
}
