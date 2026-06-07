package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun ProfileData(
    firstName: String,
    lastName: String,
    email: String
) {

    val manrope = rememberManropeFont()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileAvatar(
            letter = firstName.take(1)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "$firstName $lastName",
            fontFamily = manrope,
            fontSize = 20.sp,
            fontWeight = FontWeight.W800,
            textAlign = TextAlign.Center
        )
        Text(
            text = email,
            fontSize = 13.sp,
            color = AppColors.inActiveButtonTextColor,
            fontWeight = FontWeight.W400
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProfileDataPreview() {
    ProfileData(
        firstName = "Анна",
        lastName = "Купченко",
        email = "kupchenko@gmail.com"
    )
}