package my.tatarinov.kflow.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kflow.composeapp.generated.resources.Res
import kflow.composeapp.generated.resources.ic_users
import my.tatarinov.kflow.presentation.utils.AppColors
import my.tatarinov.kflow.presentation.utils.rememberManropeFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun WorkoutItem(
    startsAt: String,
    title: String,
    bookedCount: Int,
    capacity: Int,
    canBook: Boolean,
    isBooked: Boolean,
    onButtonBookClick: () -> Unit,
    onButtonCancelClick: () -> Unit
) {
    val manrope = rememberManropeFont()

    val background = if (bookedCount >= capacity) Color(0xFFfdfbfb) else Color.White

    val statusTextColor = if (isBooked) Color(0xFFa89cc8)
    else if (bookedCount >= capacity) AppColors.inActiveButtonTextColor
    else Color(0xFF8fb8a8)

    val statusBackgroundColor = if (isBooked) Color(0xffeae6f5)
    else if (bookedCount >= capacity) Color(0xffede6e2)
    else Color(0xFFdff0e9)

    val statusText = if (isBooked) "Записана"
    else if (bookedCount >= capacity) "Мест нет"
    else "Свободно"

    Row(
        modifier = Modifier.height(110.dp).fillMaxWidth().shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            ambientColor = Color(0x1A000000),
            spotColor = Color(0x80000000)
        ).background(color = background, shape = RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(16.dp)
        ) {
            //Дада
            Text(
                text = startsAt,
                fontSize = 11.sp,
                fontFamily = manrope,
                fontWeight = FontWeight.Normal,
                color = AppColors.inActiveButtonTextColor
            )

            //Название
            Text(
                text = title, fontSize = 15.sp, fontFamily = manrope, fontWeight = FontWeight.W700
            )

            Spacer(Modifier.weight(1f))

            //Места
            Row {
                Image(
                    painterResource(Res.drawable.ic_users),
                    contentDescription = "",
                    Modifier.size(14.dp)
                )
                Text(
                    text = "$bookedCount/$capacity",
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    color = AppColors.inActiveButtonTextColor,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.End
        ) {

            //Статус
            Box(
                modifier = Modifier.padding(top = 16.dp, end = 18.dp).height(22.dp),
            ) {

                Button(
                    onClick = {},
                    colors = ButtonColors(
                        containerColor = statusBackgroundColor,
                        contentColor = statusTextColor,
                        disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                        disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
                    ),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(vertical = 1.dp, horizontal = 10.dp),
                ) {
                    Text(
                        text = statusText,
                        fontFamily = manrope,
                        fontWeight = FontWeight.W600,
                        fontSize = 11.sp
                    )
                }

            }

            Spacer(Modifier.weight(1f))

            //Кнопка
            Box(
                modifier = Modifier.padding(end = 18.dp, bottom = 13.dp).height(30.dp),
            ) {
                if (canBook) {
                    Button(
                        onClick = { onButtonBookClick() },
                        colors = ButtonColors(
                            containerColor = AppColors.darkPrimary,
                            contentColor = Color.White,
                            disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                            disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
                        ),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(vertical = 1.dp, horizontal = 10.dp),
                    ) {
                        Text(
                            text = "Записаться",
                            fontFamily = manrope,
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp
                        )
                    }
                } else if (isBooked) {
                    OutlinedButton(
                        onClick = { onButtonCancelClick() },
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(vertical = 1.dp, horizontal = 10.dp),
                        border = BorderStroke(1.dp, color = Color(0xffa89cc8))
                    ) {
                        Text(
                            text = "Отменить",
                            fontFamily = manrope,
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            color = Color(0xffa89cc8)
                        )
                    }
                }
            }
        }

    }
}