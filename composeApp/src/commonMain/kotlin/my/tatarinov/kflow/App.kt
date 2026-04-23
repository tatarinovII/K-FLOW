package my.tatarinov.kflow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.tatarinov.kflow.di.appModule
import my.tatarinov.kflow.presentation.auth.ui.AuthScreen
import my.tatarinov.kflow.presentation.home.HomeScreen
import my.tatarinov.kflow.presentation.navigation.Route
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.Auth,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Route.Auth> {
                    AuthScreen(
                        onAuthSuccess = {
                            navController.navigate(Route.Home) {
                                popUpTo(Route.Auth) { inclusive = true}
                            }
                        }
                    )
                }
                composable<Route.Home> {
                    HomeScreen()
                }
            }

        }
    }
}