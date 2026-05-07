package my.tatarinov.kflow.presentation.auth.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.email
import kflow.composeapp.generated.resources.email_field_hint
import kflow.composeapp.generated.resources.forgot_password
import kflow.composeapp.generated.resources.ic_google
import kflow.composeapp.generated.resources.log_in_do
import kflow.composeapp.generated.resources.log_in_for_joining_workout
import kflow.composeapp.generated.resources.password
import kflow.composeapp.generated.resources.password_field_hint
import kflow.composeapp.generated.resources.sign_in_with_google
import kflow.composeapp.generated.resources.welcome_back
import my.tatarinov.kflow.presentation.auth.AuthViewModel
import my.tatarinov.kflow.presentation.auth.components.AuthTextField
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginFragment(
    viewModel: AuthViewModel, onAuthSuccess: () -> Unit
) {

    val manrope = rememberManropeFont()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isAuthenticated) {
        if (state.isAuthenticated) onAuthSuccess()
    }

    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).imePadding()
            .background(color = AppColors.backgroundColor)
    ) {
        //С возвращением...
        Text(
            text = stringResource(Res.string.welcome_back),
            fontFamily = manrope,
            fontWeight = FontWeight.W800,
            fontSize = 26.sp
        )
        //Войдите, чтобы...
        Text(
            text = stringResource(Res.string.log_in_for_joining_workout),
            fontFamily = manrope,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = AppColors.lightTextColor
        )
        //Оступ
        Spacer(modifier = Modifier.height(16.dp))
        //Поле почта
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.onLoginEmailChanged(it) },
            hint = stringResource(Res.string.email_field_hint),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textFieldTitle = stringResource(Res.string.email),
            value = state.loginEmail
        )
        //Отступ
        Spacer(modifier = Modifier.height(8.dp))
        //Поле пароль
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.onLoginPasswordChanged(it) },
            hint = stringResource(Res.string.password_field_hint),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textFieldTitle = stringResource(Res.string.password),
            value = state.loginPassword
        )
        //Отступ
        Spacer(modifier = Modifier.height(4.dp))
        //Забыли пароль?
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.forgot_password),
            textAlign = TextAlign.End,
            fontFamily = manrope,
            fontWeight = FontWeight.W600,
            fontSize = 12.sp,
            color = AppColors.darkPrimary
        )
        //Отступ
        Spacer(modifier = Modifier.height(8.dp))
        //Кнопка входа
        Button(
            modifier = Modifier.fillMaxWidth().shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(corner = CornerSize(14.dp)),
                ambientColor = Color(0x1A000000),
                spotColor = Color(0x80000000)
            ),
            onClick = { viewModel.onLoginButtonClicked() },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.darkPrimary
            )
        ) {
            Text(
                text = stringResource(Res.string.log_in_do),
                fontFamily = manrope,
                fontWeight = FontWeight.W700,
                fontSize = 15.sp
            )
        }
        //Отступ
        Spacer(modifier = Modifier.height(8.dp))
        //или
        Row {
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp).weight(0.1f),
                color = AppColors.dividerColor
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "или",
                color = AppColors.inActiveButtonTextColor,
                fontFamily = manrope,
                fontWeight = FontWeight.W600,
                fontSize = 11.sp
            )
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp).weight(0.1f),
                color = AppColors.dividerColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        //Войти через гугл
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(width = 1.dp, color = AppColors.dividerColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            )
        ) {
            Image(
                painterResource(Res.drawable.ic_google), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(Res.string.sign_in_with_google),
                fontFamily = manrope,
                fontWeight = FontWeight.W700,
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}