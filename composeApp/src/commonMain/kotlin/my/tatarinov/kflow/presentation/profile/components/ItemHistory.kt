package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun ItemHistory(
    title: String, date: String, showDivider: Boolean = true
) {
    val manrope = rememberManropeFont()
    Column {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                fontFamily = manrope,
                color = Color.Black,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp
            )
            Text(
                text = date,
                fontFamily = manrope,
                color = AppColors.inActiveButtonTextColor,
                fontWeight = FontWeight.W400,
                fontSize = 11.sp
            )
        }
        if (showDivider) HorizontalDivider(thickness = 0.5f.dp, color = AppColors.inActiveButtonTextColor)
    }
}

@Composable
@Preview
fun ItemHistoryPreview() {
    ItemHistory(
        title = "Растяжка", date = "12 апреля"
    )
}