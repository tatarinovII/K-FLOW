package my.tatarinov.kflow.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.ic_logout
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun LogOutButton(
    onLogOutButtonClicked: () -> Unit
) {

    val manrope = rememberManropeFont()

    Button(
        modifier = Modifier.fillMaxWidth().shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            ambientColor = Color(0x1A000000),
            spotColor = Color(0x80000000)
        ),
        onClick = onLogOutButtonClicked,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, contentColor = Color(0xFFe07060)
        )
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_logout), contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Выйти", fontFamily = manrope, fontSize = 15.sp, fontWeight = FontWeight.W600
        )
    }
}

@Composable
@Preview
private fun LogOutButtonPreview() {
    LogOutButton(
        onLogOutButtonClicked = {})
}