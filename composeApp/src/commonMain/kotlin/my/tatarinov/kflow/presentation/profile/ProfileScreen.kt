package my.tatarinov.kflow.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.tatarinov.kflow.presentation.profile.components.ProfileData
import my.tatarinov.kflow.presentation.profile.ProfileUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (val s = state) {
        is ProfileUiState.Default -> {

        }
        ProfileUiState.Loading -> {}
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    ProfileScreen()
}
