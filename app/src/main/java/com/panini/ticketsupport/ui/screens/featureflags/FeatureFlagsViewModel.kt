package com.panini.ticketsupport.ui.screens.featureflags

import androidx.lifecycle.ViewModel
import com.panini.ticketsupport.domain.featureflags.FeatureFlagsManager
import kotlinx.coroutines.flow.StateFlow

class FeatureFlagsViewModel : ViewModel() {

    val canCreateTickets: StateFlow<Boolean> = FeatureFlagsManager.canCreateTickets
    val canUpdatePriority: StateFlow<Boolean> = FeatureFlagsManager.canUpdatePriority

    fun toggleCreateTickets(enabled: Boolean) {
        FeatureFlagsManager.setCanCreateTickets(enabled)
    }

    fun toggleUpdatePriority(enabled: Boolean) {
        FeatureFlagsManager.setCanUpdatePriority(enabled)
    }
}
