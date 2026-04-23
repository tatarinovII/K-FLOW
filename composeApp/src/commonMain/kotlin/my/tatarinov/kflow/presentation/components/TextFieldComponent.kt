package my.tatarinov.kflow.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont

@Composable
fun AuthTextField(
    value: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    hint: String,
    keyBoardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation? = null,
    textFieldTitle: String
) {
    Column {
        val manrope = rememberManropeFont()
        Text(
            text = textFieldTitle,
            fontFamily = manrope,
            fontWeight = FontWeight.W600,
            color = AppColors.lightTextColor,
            fontSize = 11.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint, color = AppColors.hintColor
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.darkPrimary,
                unfocusedBorderColor = AppColors.darkPrimary,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            keyboardOptions = keyBoardOptions,
            visualTransformation = visualTransformation ?: VisualTransformation.None
        )
    }
}