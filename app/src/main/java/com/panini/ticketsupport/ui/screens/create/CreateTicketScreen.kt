package com.panini.ticketsupport.ui.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.panini.ticketsupport.domain.model.TicketCategory
import com.panini.ticketsupport.ui.components.AppButton
import com.panini.ticketsupport.ui.components.AppTextField
import com.panini.ticketsupport.ui.components.PaniniScaffold
import com.panini.ticketsupport.ui.theme.AppBorder
import com.panini.ticketsupport.ui.theme.AppError
import com.panini.ticketsupport.ui.theme.AppPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketScreen(onBack: () -> Unit, onTicketCreated: () -> Unit) {
    val viewModel: CreateTicketViewModel = viewModel(factory = CreateTicketViewModel.Factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onTicketCreated()
    }

    PaniniScaffold(title = "Nuevo Ticket", showBack = true, onBack = onBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppTextField(
                value = uiState.title,
                onValueChange = viewModel::onTitleChange,
                label = "Título *",
                placeholder = "Ej: Faltante de stickers selección ...",
                modifier = Modifier.fillMaxWidth()
            )

            AppTextField(
                value = uiState.description,
                onValueChange = viewModel::onDescriptionChange,
                label = "Descripción",
                placeholder = "Detalle del incidente...",
                singleLine = false,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            AppTextField(
                value = uiState.provider,
                onValueChange = viewModel::onProviderChange,
                label = "Proveedor *",
                placeholder = "Nombre del proveedor",
                modifier = Modifier.fillMaxWidth()
            )

            // Priority dropdown
            var priorityExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = priorityExpanded,
                onExpandedChange = { priorityExpanded = it }
            ) {
                OutlinedTextField(
                    value = uiState.priority.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Prioridad") },
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
                                viewModel.onPriorityChange(priority)
                                priorityExpanded = false
                            }
                        )
                    }
                }
            }

            // Category dropdown
            var categoryExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = it }
            ) {
                OutlinedTextField(
                    value = uiState.category.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
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
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    TicketCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.label) },
                            onClick = {
                                viewModel.onCategoryChange(category)
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage!!,
                    color = AppError,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            AppButton(
                text = "Crear ticket",
                onClick = viewModel::createTicket,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
