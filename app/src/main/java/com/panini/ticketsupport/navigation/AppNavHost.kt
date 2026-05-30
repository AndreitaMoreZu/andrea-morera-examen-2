package com.panini.ticketsupport.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.panini.ticketsupport.ui.screens.create.CreateTicketScreen
import com.panini.ticketsupport.ui.screens.detail.TicketDetailScreen
import com.panini.ticketsupport.ui.screens.featureflags.FeatureFlagsScreen
import com.panini.ticketsupport.ui.screens.login.LoginScreen
import com.panini.ticketsupport.ui.screens.tickets.TicketListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN,
        modifier = modifier
    ) {
        composable(AppDestinations.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppDestinations.TICKET_LIST) {
                        popUpTo(AppDestinations.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.TICKET_LIST) {
            TicketListScreen(
                onTicketClick = { ticketId ->
                    navController.navigate(AppDestinations.ticketDetailRoute(ticketId))
                },
                onCreateTicket = {
                    navController.navigate(AppDestinations.CREATE_TICKET)
                },
                onFeatureFlags = {
                    navController.navigate(AppDestinations.FEATURE_FLAGS)
                }
            )
        }

        composable(
            route = AppDestinations.TICKET_DETAIL,
            arguments = listOf(navArgument("ticketId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId") ?: return@composable
            TicketDetailScreen(
                ticketId = ticketId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppDestinations.CREATE_TICKET) {
            CreateTicketScreen(
                onBack = { navController.popBackStack() },
                onTicketCreated = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppDestinations.FEATURE_FLAGS) {
            FeatureFlagsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
