package my.tatarinov.kflow.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import my.tatarinov.kflow.presentation.profile.states.ProfileScreenDefaultState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (val s = state) {
        is ProfileUiState.Default -> {
            ProfileScreenDefaultState(
                firstName = s.firstName,
                lastName = s.lastName,
                email = s.email,
                remainWorkout = s.workoutRemain,
                onButtonBuyClick = {},
                historyList = s.historyList,
                onLogOutButtonClicked = {})
        }
        ProfileUiState.Loading -> {}
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    ProfileScreen()
}
