package my.tatarinov.kflow.presentation.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.create_account
import kflow.composeapp.generated.resources.email
import kflow.composeapp.generated.resources.email_field_hint
import kflow.composeapp.generated.resources.female
import kflow.composeapp.generated.resources.hint_last_name
import kflow.composeapp.generated.resources.hint_name
import kflow.composeapp.generated.resources.just_a_few_seconds
import kflow.composeapp.generated.resources.male
import kflow.composeapp.generated.resources.password
import kflow.composeapp.generated.resources.password_field_hint
import kflow.composeapp.generated.resources.sex
import kflow.composeapp.generated.resources.sign_up_do
import kflow.composeapp.generated.resources.textfield_last_name_title
import kflow.composeapp.generated.resources.textfield_name_title
import my.tatarinov.kflow.presentation.auth.AuthViewModel
import my.tatarinov.kflow.presentation.auth.components.AuthTabs
import my.tatarinov.kflow.presentation.auth.components.AuthTextField
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterFragment(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {

    val manrope = rememberManropeFont()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isAuthenticated) {
        if (state.isAuthenticated) onAuthSuccess()
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(color = AppColors.backgroundColor)
    ) {
        //Создать аккаунт
        Text(
            text = stringResource(Res.string.create_account),
            fontFamily = manrope,
            fontWeight = FontWeight.W800,
            fontSize = 26.sp
        )
        //Несколько сек..
        Text(
            text = stringResource(Res.string.just_a_few_seconds),
            fontFamily = manrope,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = AppColors.lightTextColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Пол
        Text(
            text = stringResource(Res.string.sex),
            fontFamily = manrope,
            fontWeight = FontWeight.W600,
            color = AppColors.lightTextColor,
            fontSize = 11.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        //Табы пол
        AuthTabs(
            firstTabName = stringResource(Res.string.female),
            secondTabName = stringResource(Res.string.male),
            onFirstTabClicked = {
                viewModel.onSexSelected(0)
            },
            onSecondTabClicked = {
                viewModel.onSexSelected(1)
            },
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = state.selectedSexIndex
        )
        Spacer(modifier = Modifier.height(8.dp))
        //Имя/фамилия
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            //Поле имя
            Box(modifier = Modifier.fillMaxWidth().weight(0.1f)) {
                AuthTextField(
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onRegisterFirstNameChanged(it) },
                    hint = stringResource(Res.string.hint_name),
                    keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textFieldTitle = stringResource(Res.string.textfield_name_title),
                    value = state.regFirstName
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            //Поле фамилия
            Box(modifier = Modifier.fillMaxWidth().weight(0.1f)) {
                AuthTextField(
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onRegisterLastNameChanged(it) },
                    hint = stringResource(Res.string.hint_last_name),
                    keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textFieldTitle = stringResource(Res.string.textfield_last_name_title),
                    value = state.regLastName
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        //Поле почта
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.onRegisterEmailChanged(it) },
            hint = stringResource(Res.string.email_field_hint),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textFieldTitle = stringResource(Res.string.email),
            value = state.regEmail
        )
        Spacer(modifier = Modifier.height(8.dp))
        //Поле пароль
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.onRegisterPasswordChanged(it) },
            hint = stringResource(Res.string.password_field_hint),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textFieldTitle = stringResource(Res.string.password),
            value = state.regPassword
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Кнопка регистрации
        Button(
            modifier = Modifier.fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(corner = CornerSize(14.dp)),
                    ambientColor = Color(0x1A000000),
                    spotColor = Color(0x80000000)
                ),
            onClick = { viewModel.onRegisterButtonClicked() },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.darkPrimary
            )
        ) {
            Text(
                text = stringResource(Res.string.sign_up_do),
                fontFamily = manrope,
                fontWeight = FontWeight.W700,
                fontSize = 15.sp
            )
        }
    }
}