package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun ProfileBalance(
    remainWorkout: Int,
    onButtonBuyClick: () -> Unit
) {
    val manrope = rememberManropeFont()
    Column(
        modifier = Modifier.fillMaxWidth().shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            ambientColor = Color(0x1A000000),
            spotColor = Color(0x80000000)
        ).background(color = Color.White, shape = RoundedCornerShape(20.dp)).padding(18.dp)
    ) {
        Text(
            text = "МОИ ЗАНЯТИЯ",
            color = AppColors.inActiveButtonTextColor,
            fontSize = 12.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Text(
            text = remainWorkout.toString(),
            color = Color.Black,
            fontFamily = manrope,
            fontWeight = FontWeight.W800,
            fontSize = 46.sp,
            textAlign = TextAlign.Start
        )
        Text(
            text = "занятий осталось",
            color = AppColors.lightTextColor,
            fontSize = 12.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {onButtonBuyClick()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFf2e4e0),
                contentColor = AppColors.darkPrimary
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "+ Купить занятия",
                fontWeight = FontWeight.W700,
                fontFamily = manrope,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}


@Composable
@Preview
private fun ProfileBalancePreview() {
    ProfileBalance(10, {})
}