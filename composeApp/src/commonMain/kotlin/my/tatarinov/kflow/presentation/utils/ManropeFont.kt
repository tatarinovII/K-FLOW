package my.tatarinov.kflow.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.manrope_bold
import kflow.composeapp.generated.resources.manrope_extrabold
import kflow.composeapp.generated.resources.manrope_medium
import kflow.composeapp.generated.resources.manrope_regular
import org.jetbrains.compose.resources.Font


@Composable
fun rememberManropeFont(): FontFamily {
    val regular = Font(Res.font.manrope_regular, FontWeight.Normal)
    val medium = Font(Res.font.manrope_medium, FontWeight.Medium)
    val bold = Font(Res.font.manrope_bold, FontWeight.Bold)
    val extraBold = Font(Res.font.manrope_extrabold, FontWeight.ExtraBold)
    return remember(regular, medium, bold, extraBold) {
        FontFamily(regular, medium, bold, extraBold)
    }
}