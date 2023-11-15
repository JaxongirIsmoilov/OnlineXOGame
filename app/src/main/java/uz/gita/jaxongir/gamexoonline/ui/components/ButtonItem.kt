package uz.gita.jaxongir.gamexoonline.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.gamexoonline.ui.theme.Card
import uz.gita.jaxongir.gamexoonline.ui.theme.Primary38
import uz.gita.jaxongir.gamexoonline.ui.theme.RoundedShape
import uz.gita.jaxongir.gamexoonline.ui.theme.Typography


@Composable
fun ButtonItem(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .size(width = 300.dp, height = 56.dp)
            .clip(RoundedShape.large)
            .border(1.dp, Card, RoundedShape.large)
            .background(Primary38),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Text(text = text, style = Typography.labelMedium)
    }
}

@Preview
@Composable
fun PreviewButtonItem() {
    EditTextFiled()
}