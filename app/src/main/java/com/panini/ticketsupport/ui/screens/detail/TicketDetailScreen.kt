package com.panini.ticketsupport.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.domain.model.TicketStatus
import com.panini.ticketsupport.ui.components.PaniniScaffold
import com.panini.ticketsupport.ui.components.PriorityBadge
import com.panini.ticketsupport.ui.components.StatusBadge
import com.panini.ticketsupport.ui.theme.AppBorder
import com.panini.ticketsupport.ui.theme.AppPrimary
import com.panini.ticketsupport.ui.theme.AppSecondaryText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(ticketId: String, onBack: () -> Unit) {
    val viewModel: TicketDetailViewModel = viewModel(factory = TicketDetailViewModel.factory(ticketId))
    val ticket by viewModel.ticket.collectAsStateWithLifecycle()
    val canUpdatePriority by viewModel.canUpdatePriority.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
        }
    }

    PaniniScaffold(
        title = "Detalle del Ticket",
        showBack = true,
        onBack = onBack,
        snackbarHostState = snackbarHostState
    ) { paddingValues ->
        if (ticket == null) {
            Text(
                text = "Ticket no encontrado",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
            return@PaniniScaffold
        }

        val t = ticket!!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PriorityBadge(priority = t.priority)
                StatusBadge(status = t.status)
            }

            Text(text = t.title, style = MaterialTheme.typography.headlineMedium)
            HorizontalDivider(color = AppBorder)

            DetailRow(label = "Proveedor", value = t.provider)
            DetailRow(label = "Categoría", value = t.category.label)
            DetailRow(label = "Reportado por", value = t.reportedBy)
            DetailRow(label = "Fecha de creación", value = t.createdAt)
            DetailRow(label = "ID", value = t.id)

            Text(
                text = "Descripción",
                style = MaterialTheme.typography.labelLarge,
                color = AppSecondaryText
            )
            Text(text = t.description, style = MaterialTheme.typography.bodyMedium)

            HorizontalDivider(color = AppBorder)

            // Status update dropdown
            var statusExpanded by remember { mutableStateOf(false) }
            Text(
                text = "Actualizar estado",
                style = MaterialTheme.typography.labelLarge,
                color = AppSecondaryText
            )
            ExposedDropdownMenuBox(
                expanded = statusExpanded,
                onExpandedChange = { statusExpanded = it }
            ) {
                OutlinedTextField(
                    value = t.status.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado actual") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AppPrimary,
                        unfocusedBorderColor = AppBorder
                    ),
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = statusExpanded,
                    onDismissRequest = { statusExpanded = false }
                ) {
                    TicketStatus.entries.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status.label) },
                            onClick = {
                                viewModel.updateStatus(status)
                                statusExpanded = false
                            }
                        )
                    }
                }
            }

            // Priority update dropdown — only visible when feature flag is enabled
            if (canUpdatePriority) {
                var priorityExpanded by remember { mutableStateOf(false) }
                Text(
                    text = "Actualizar prioridad",
                    style = MaterialTheme.typography.labelLarge,
                    color = AppSecondaryText
                )
                ExposedDropdownMenuBox(
                    expanded = priorityExpanded,
                    onExpandedChange = { priorityExpanded = it }
                ) {
                    OutlinedTextField(
                        value = t.priority.label,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Prioridad actual") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = priorityExpanded) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AppPrimary,
                            unfocusedBorderColor = AppBorder
                        ),
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = priorityExpanded,
                        onDismissRequest = { priorityExpanded = false }
                    ) {
                        Priority.entries.forEach { priority ->
                            DropdownMenuItem(
                                text = { Text(priority.label) },
                                onClick = {
                                    viewModel.updatePriority(priority)
                                    priorityExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = AppSecondaryText
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
