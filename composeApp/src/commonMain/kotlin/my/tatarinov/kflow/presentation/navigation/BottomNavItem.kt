package my.tatarinov.kflow.presentation.navigation

import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.ic_home
import kflow.composeapp.generated.resources.ic_profile
import org.jetbrains.compose.resources.DrawableResource

data class BottomNavItem(
    val route: Any,
    val title: String,
    val icon: DrawableResource
)

val bottomNavItems = listOf(
    BottomNavItem(Route.Home, "Главная", Res.drawable.ic_home),
    BottomNavItem(Route.Profile, "Профиль", Res.drawable.ic_profile)
)