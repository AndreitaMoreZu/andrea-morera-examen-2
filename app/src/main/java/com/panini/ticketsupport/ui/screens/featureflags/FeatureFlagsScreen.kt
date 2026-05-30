package com.panini.ticketsupport.ui.screens.featureflags

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.panini.ticketsupport.ui.components.PaniniScaffold
import com.panini.ticketsupport.ui.theme.AppBorder
import com.panini.ticketsupport.ui.theme.AppPrimary
import com.panini.ticketsupport.ui.theme.AppSecondaryText
import com.panini.ticketsupport.ui.theme.AppSurface

@Composable
fun FeatureFlagsScreen(onBack: () -> Unit) {
    val viewModel: FeatureFlagsViewModel = viewModel()
    val canCreateTickets by viewModel.canCreateTickets.collectAsStateWithLifecycle()
    val canUpdatePriority by viewModel.canUpdatePriority.collectAsStateWithLifecycle()

    PaniniScaffold(title = "Feature Flags", showBack = true, onBack = onBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Controla las funcionalidades activas en la aplicación. Los cambios se aplican inmediatamente.",
                style = MaterialTheme.typography.bodyMedium,
                color = AppSecondaryText
            )
            Spacer(modifier = Modifier.height(8.dp))

            FeatureFlagRow(
                title = "Creación de tickets",
                description = "Habilita el botón para registrar nuevos tickets de soporte.",
                enabled = canCreateTickets,
                onToggle = viewModel::toggleCreateTickets
            )

            FeatureFlagRow(
                title = "Actualización de prioridad",
                description = "Habilita la sección para cambiar la prioridad de un ticket en el detalle.",
                enabled = canUpdatePriority,
                onToggle = viewModel::toggleUpdatePriority
            )
        }
    }
}

@Composable
private fun FeatureFlagRow(
    title: String,
    description: String,
    enabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, AppBorder),
        colors = CardDefaults.cardColors(containerColor = AppSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppSecondaryText
                )
            }
            Switch(
                checked = enabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AppSurface,
                    checkedTrackColor = AppPrimary
                )
            )
        }
    }
}
