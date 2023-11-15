package uz.gita.jaxongir.gamexoonline.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.jaxongir.gamexoonline.R
import uz.gita.jaxongir.gamexoonline.data.common.UserUICommon

@Composable
fun UsersItem(
    player: UserUICommon,
    click: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF3F51B5),
                        Color(0xFF253694)
                    )
                )
            )
            .clickable {
                click(player.uuid, player.name)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = player.name,
                style = TextStyle(
                    color = Color(0xffF5F5F5),
                    fontFamily = FontFamily(listOf(Font(R.font.irish_grover_regular))),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UsersItem(UserUICommon("", "")) { id, name -> }
}