package my.tatarinov.kflow.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.ic_calendar
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreenWorkoutsRemain(workoutRemain: Int) {
    val manrope = rememberManropeFont()
    Row(
        modifier = Modifier.fillMaxWidth().shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            ambientColor = Color(0x1A000000),
            spotColor = Color(0x80000000)
        ).background(color = Color.White, shape = RoundedCornerShape(20.dp)).padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "ОСТАЛОСЬ ЗАНЯТИЙ",
                color = AppColors.inActiveButtonTextColor,
                fontSize = 12.sp,
                fontFamily = manrope,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = workoutRemain.toString(),
                color = Color.Black,
                fontFamily = manrope,
                fontWeight = FontWeight.W800,
                fontSize = 46.sp,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.ic_calendar),
            contentDescription = "",
            modifier = Modifier.background(
                color = AppColors.avatarColor,
                shape = RoundedCornerShape(corner = CornerSize(18.dp))
            ).padding(16.dp),

            )
    }
}

@Composable
@Preview
private fun HomeScreenWorkoutsRemainPreview() {
    HomeScreenWorkoutsRemain(8)
}