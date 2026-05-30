package com.panini.ticketsupport.ui.screens.tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.panini.ticketsupport.ui.components.PaniniScaffold
import com.panini.ticketsupport.ui.components.TicketCard
import com.panini.ticketsupport.ui.theme.AppPrimary
import com.panini.ticketsupport.ui.theme.AppSurface

@Composable
fun TicketListScreen(
    onTicketClick: (String) -> Unit,
    onCreateTicket: () -> Unit,
    onFeatureFlags: () -> Unit
) {
    val viewModel: TicketListViewModel = viewModel(factory = TicketListViewModel.Factory)
    val tickets by viewModel.tickets.collectAsStateWithLifecycle()
    val canCreateTickets by viewModel.canCreateTickets.collectAsStateWithLifecycle()

    PaniniScaffold(
        title = "Tickets de Soporte",
        topBarActions = {
            IconButton(onClick = onFeatureFlags) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Feature Flags",
                    tint = AppSurface
                )
            }
        },
        floatingActionButton = {
            if (canCreateTickets) {
                FloatingActionButton(
                    onClick = onCreateTicket,
                    containerColor = AppPrimary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Nuevo ticket",
                        tint = AppSurface
                    )
                }
            }
        }
    ) { paddingValues ->
        if (tickets.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No hay tickets disponibles")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = 88.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tickets, key = { it.id }) { ticket ->
                    TicketCard(
                        ticket = ticket,
                        onClick = { onTicketClick(ticket.id) }
                    )
                }
            }
        }
    }
}
