package my.tatarinov.kflow.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.tatarinov.kflow.presentation.auth.ui.AuthScreen
import my.tatarinov.kflow.presentation.home.HomeScreen
import my.tatarinov.kflow.presentation.navigation.Route
import my.tatarinov.kflow.presentation.utils.AppColors

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    Scaffold(
        containerColor = AppColors.backgroundColor
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Auth,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Auth> {
                AuthScreen(
                    onAuthSuccess = {
                        navController.navigate(Route.Home) {
                            popUpTo(Route.Auth) { inclusive = true }
                        }
                    })
            }
            composable<Route.Home> {
                HomeScreen()
            }
        }

    }
}