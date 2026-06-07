package my.tatarinov.kflow.presentation.profile.states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.profile.components.ProfileBalance
import my.tatarinov.kflow.presentation.profile.components.ProfileData
import my.tatarinov.kflow.presentation.profile.components.ProfileHistoryList
import my.tatarinov.kflow.presentation.profile.components.ProfileLogOutButton
import my.tatarinov.kflow.presentation.profile.models.HistoryItem
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun ProfileScreenDefaultState(
    firstName: String,
    lastName: String,
    email: String,
    remainWorkout: Int,
    onButtonBuyClick: () -> Unit,
    historyList: List<HistoryItem>,
    onLogOutButtonClicked: () -> Unit
) {

    val manrope = rememberManropeFont()

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(80.dp))
        ProfileData(firstName, lastName, email)
        Spacer(Modifier.height(30.dp))
        ProfileBalance(remainWorkout, onButtonBuyClick)
        Spacer(Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            ProfileHistoryList(historyList, modifier = Modifier.weight(1f, fill = true))
            Spacer(Modifier.height(24.dp))
            ProfileLogOutButton(onLogOutButtonClicked)
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                text = "k-flow · версия 1.0.0",
                color = AppColors.inActiveButtonTextColor,
                textAlign = TextAlign.Center,
                fontFamily = manrope,
                fontWeight = FontWeight.W400,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ProfileScreenDefaultStatePreview() {
    ProfileScreenDefaultState(
        "Анна", "Купченко", "kupchenko@gmail.com", 10, {}, listOf(
            HistoryItem(
                title = "Растяжка", date = "12 апреля"
            ), HistoryItem(
                title = "Силовая", date = "14 апреля"
            ), HistoryItem(
                title = "Растяжка", date = "15 апреля"
            )
        ), onLogOutButtonClicked = {})
}
