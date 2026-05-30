package com.panini.ticketsupport.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = AppPrimary,
    onPrimary = AppSurface,
    primaryContainer = AppPrimaryLight,
    onPrimaryContainer = AppPrimaryDark,
    background = AppBackground,
    onBackground = AppPrimary,
    surface = AppSurface,
    onSurface = AppPrimary,
    surfaceVariant = AppSurfaceVariant,
    onSurfaceVariant = AppSecondaryText,
    outline = AppBorder,
    error = AppError
)

@Composable
fun PaniniTicketSupportTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
