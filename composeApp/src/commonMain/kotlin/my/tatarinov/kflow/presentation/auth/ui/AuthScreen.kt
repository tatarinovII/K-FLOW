package my.tatarinov.kflow.presentation.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.app_name
import kflow.composeapp.generated.resources.log_in
import kflow.composeapp.generated.resources.sign_up
import kotlinx.coroutines.launch
import my.tatarinov.kflow.presentation.auth.AuthViewModel
import my.tatarinov.kflow.presentation.auth.components.AuthTabs
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(), onAuthSuccess: () -> Unit
) {

    val manrope = rememberManropeFont()
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(pagerState.currentPage) {
        focusManager.clearFocus()
        keyboardController?.hide()
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color(0xfff7f3f0))
            .padding(top = 40.dp).pointerInput(Unit) {
                detectTapGestures (onTap = {focusManager.clearFocus()} )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Название
        Text(
            text = stringResource(Res.string.app_name),
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = manrope,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.7.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        //Табы
        AuthTabs(
            firstTabName = stringResource(Res.string.log_in),
            secondTabName = stringResource(Res.string.sign_up),
            selectedIndex = pagerState.currentPage,
            onFirstTabClicked = {
                scope.launch { pagerState.animateScrollToPage(0) }
            },
            onSecondTabClicked = {
                scope.launch { pagerState.animateScrollToPage(1) }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().weight(1f),
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top
        ) { page ->
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                when (page) {
                    0 -> LoginFragment(viewModel, onAuthSuccess)
                    1 -> RegisterFragment(viewModel, onAuthSuccess)
                }
            }
        }
    }
}