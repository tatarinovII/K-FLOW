package my.tatarinov.kflow.presentation.auth.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun AuthTabs(
    firstTabName: String,
    secondTabName: String,
    selectedIndex: Int,
    onFirstTabClicked: () -> Unit,
    onSecondTabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        AuthTab(
            name = firstTabName,
            isSelected = selectedIndex == 0,
            onClick = onFirstTabClicked,
            modifier = Modifier.weight(1f)
        )
        AuthTab(
            name = secondTabName,
            isSelected = selectedIndex == 1,
            onClick = onSecondTabClicked,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AuthTab(
    name: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    val manrope = rememberManropeFont()
    val elevation = if (isSelected) 8.dp else 0.dp
    val background = if (isSelected) Color.White else Color.Transparent
    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else AppColors.inActiveButtonTextColor,
        label = "tab_text_color"
    )
    val shape = RoundedCornerShape(11.dp)

    Box(
        modifier = modifier.shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color(0x1A000000),
            spotColor = Color(0x80000000)
        ).background(
            background, shape = shape
        ).clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(10.dp),
            fontSize = 14.sp,
            fontFamily = manrope,
            fontWeight = FontWeight.W700,
            color = textColor
        )
    }
}