package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun ProfileAvatar(
    letter: String
) {
    val manrope = rememberManropeFont()
    Box(
        modifier = Modifier
            .size(70.dp)
            .border(1.dp, AppColors.darkPrimary, CircleShape)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(63.dp)
                .clip(CircleShape)
                .background(AppColors.avatarColor)
        )
        Text(
            text = letter,
            color = AppColors.darkPrimary,
            fontFamily = manrope,
            fontSize = 30.sp,
            fontWeight = FontWeight.W700
        )
    }
}

@Composable
@Preview
private fun ProfileAvatarPreview() {
    ProfileAvatar(
        "A"
    )
}