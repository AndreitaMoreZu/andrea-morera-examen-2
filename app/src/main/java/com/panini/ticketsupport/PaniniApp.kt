package com.panini.ticketsupport

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.panini.ticketsupport.navigation.AppNavHost
import com.panini.ticketsupport.ui.theme.PaniniTicketSupportTheme

@Composable
fun PaniniApp() {
    PaniniTicketSupportTheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController)
    }
}
