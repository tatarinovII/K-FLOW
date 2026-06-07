package my.tatarinov.kflow.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.home.components.HomeScreenAvatar
import my.tatarinov.kflow.presentation.home.components.HomeScreenWorkoutsRemain
import my.tatarinov.kflow.presentation.home.components.WorkoutItem
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.fadingTopEdge
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(), toAuthScreen: () -> Unit
) {
    val manrope = rememberManropeFont()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.backToAuth) {
        if (state.backToAuth) toAuthScreen()
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(color = AppColors.backgroundColor)
            .padding(top = 60.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            //Заголовок
            Column {
                Text(
                    text = state.date,
                    fontFamily = manrope,
                    fontWeight = FontWeight.W500,
                    color = AppColors.inActiveButtonTextColor,
                    fontSize = 12.sp
                )
                Text(
                    text = "Привет, ${state.firstName} 👋🏼",
                    fontWeight = FontWeight.W800,
                    fontFamily = manrope,
                    color = Color.Black,
                    fontSize = 26.sp
                )
            }
            Spacer(Modifier.weight(1f))
            //Аватар
            HomeScreenAvatar(state.firstName[0].toString())
        }
        Spacer(modifier = Modifier.height(32.dp))

        //Абонемент
        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            HomeScreenWorkoutsRemain(state.workoutsRemain)
        }

        Spacer(Modifier.height(30.dp))
        //Ближайшие занятия
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Ближайшие тренировки",
            fontFamily = manrope,
            fontWeight = FontWeight.W800,
            fontSize = 17.sp,
            color = Color.Black
        )

        Spacer(Modifier.height(20.dp))

        val listState = rememberLazyListState()
        val showTopFade by remember {
            derivedStateOf { listState.canScrollBackward }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
                .then(if (showTopFade) Modifier.fadingTopEdge(24.dp) else Modifier),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = state.upcomingWorkouts, key = { it.id }) { workout ->
                WorkoutItem(
                    title = workout.title,
                    bookedCount = workout.bookedCount,
                    capacity = workout.capacity,
                    canBook = workout.canBook,
                    isBooked = workout.isBooked,
                    startsAt = workout.startsAt,
                    onButtonBookClick = { viewModel.onButtonBookClicked(workout.id) },
                    onButtonCancelClick = { viewModel.onButtonCancelClicked(workout.id) })
            }
        }
    }
}