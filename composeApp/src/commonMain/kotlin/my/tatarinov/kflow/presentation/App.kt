package my.tatarinov.kflow.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import my.tatarinov.kflow.presentation.auth.ui.AuthScreen
import my.tatarinov.kflow.presentation.home.HomeScreen
import my.tatarinov.kflow.presentation.navigation.Route
import my.tatarinov.kflow.presentation.navigation.bottomNavItems
import my.tatarinov.kflow.presentation.profile.ProfileScreen
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App() {
    val manrope = rememberManropeFont()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = bottomNavItems.any { item ->
        backStackEntry?.destination?.hasRoute(item.route::class) == true
    }
    Scaffold(
        containerColor = AppColors.backgroundColor, bottomBar = {
            if (showBottomBar) {
                Column {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = AppColors.dividerColor
                    )
                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 0.dp,
                        modifier = Modifier.height(80.dp),
                        windowInsets = WindowInsets(bottom = 4.dp)

                    ) {
                        bottomNavItems.forEach { item ->
                            val selected =
                                backStackEntry?.destination?.hasRoute(item.route::class) == true
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(item.icon),
                                        contentDescription = item.title
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.title,
                                        fontFamily = manrope,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.W700
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = AppColors.darkPrimary,
                                    selectedTextColor = AppColors.darkPrimary,
                                    unselectedIconColor = AppColors.inActiveButtonTextColor,
                                    unselectedTextColor = AppColors.inActiveButtonTextColor,
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            }
        }) { innerPadding ->
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
                HomeScreen(
                    toAuthScreen = {
                        navController.navigate(Route.Auth) {
                            popUpTo(0) { inclusive = true }
                        }
                    })
            }
            composable<Route.Profile> { ProfileScreen() }
        }

    }
}