package com.panini.ticketsupport.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.panini.ticketsupport.ui.theme.AppPrimary
import com.panini.ticketsupport.ui.theme.AppSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaniniScaffold(
    title: String,
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null,
    snackbarHostState: SnackbarHostState? = null,
    topBarActions: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, color = AppSurface) },
                navigationIcon = {
                    if (showBack && onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Regresar",
                                tint = AppSurface
                            )
                        }
                    }
                },
                actions = { topBarActions() },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppPrimary
                )
            )
        },
        snackbarHost = {
            if (snackbarHostState != null) {
                SnackbarHost(hostState = snackbarHostState)
            }
        },
        floatingActionButton = floatingActionButton
    ) { paddingValues ->
        content(paddingValues)
    }
}
