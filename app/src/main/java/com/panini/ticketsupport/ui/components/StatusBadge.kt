package com.panini.ticketsupport.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.panini.ticketsupport.domain.model.TicketStatus
import com.panini.ticketsupport.ui.theme.StatusClosed
import com.panini.ticketsupport.ui.theme.StatusInProgress
import com.panini.ticketsupport.ui.theme.StatusOpen
import com.panini.ticketsupport.ui.theme.StatusResolved

@Composable
fun StatusBadge(status: TicketStatus, modifier: Modifier = Modifier) {
    val color = when (status) {
        TicketStatus.OPEN -> StatusOpen
        TicketStatus.IN_PROGRESS -> StatusInProgress
        TicketStatus.RESOLVED -> StatusResolved
        TicketStatus.CLOSED -> StatusClosed
    }
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = color.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, color),
        modifier = modifier
    ) {
        Text(
            text = status.label,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}
