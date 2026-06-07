package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.profile.models.HistoryItem
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun HistoryList(
    historyList: List<HistoryItem>
) {
    val manrope = rememberManropeFont()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "ИСТОРИЯ ПОСЕЩЕНИЙ",
            color = AppColors.lightTextColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.W700,
            fontFamily = manrope
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = Color(0x1A000000),
                    spotColor = Color(0x80000000)
                ).background(color = Color.White, shape = RoundedCornerShape(20.dp))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(
                    items = historyList,
                    key = {it.date}
                ) {
                    ItemHistory(
                        title = it.title,
                        date = it.date
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryListPreview() {
    HistoryList(
        listOf(
            HistoryItem(
                title = "Растяжка", date = "12 апреля"
            ),
            HistoryItem(
                title = "Силовая", date = "14 апреля"
            ),
        )
    )
}