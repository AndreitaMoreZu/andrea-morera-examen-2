package com.panini.ticketsupport.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.panini.ticketsupport.domain.model.Priority
import com.panini.ticketsupport.ui.theme.PriorityCritical
import com.panini.ticketsupport.ui.theme.PriorityHigh
import com.panini.ticketsupport.ui.theme.PriorityLow
import com.panini.ticketsupport.ui.theme.PriorityMedium

@Composable
fun PriorityBadge(priority: Priority, modifier: Modifier = Modifier) {
    val color = when (priority) {
        Priority.CRITICAL -> PriorityCritical
        Priority.HIGH -> PriorityHigh
        Priority.MEDIUM -> PriorityMedium
        Priority.LOW -> PriorityLow
    }
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = color,
        modifier = modifier
    ) {
        Text(
            text = priority.label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}
