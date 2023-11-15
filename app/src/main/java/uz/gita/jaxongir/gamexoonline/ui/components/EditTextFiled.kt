package uz.gita.jaxongir.gamexoonline.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.gamexoonline.R
import uz.gita.jaxongir.gamexoonline.ui.theme.Card
import uz.gita.jaxongir.gamexoonline.ui.theme.RoundedShape
import uz.gita.jaxongir.gamexoonline.ui.theme.TextColor
import uz.gita.jaxongir.gamexoonline.ui.theme.Typography
import uz.gita.jaxongir.gamexoonline.ui.theme.White38

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFiled(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    placeHolder: String = "",
    onChange: (String) -> Unit = {},
    isLeadingIcon: Boolean = false,
    readOnly: Boolean = false,
    onClickLeadingIcon: () -> Unit = {},
    textStyle: TextStyle = Typography.titleSmall
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        value = text,
        onValueChange = onChange,
        label = {
            Text(
                text = hint,
                style = Typography.labelSmall,
                color = TextColor
            )
        },
        shape = RoundedShape.medium,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Card,
            unfocusedBorderColor = Card
        ),
        trailingIcon = {
            if (isLeadingIcon) {
                IconButton(onClick = onClickLeadingIcon) {
                    Icon(
                        painter = painterResource(id = R.drawable.copy),
                        contentDescription = "Copy button",
                        tint = Card
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = placeHolder,
                style = textStyle,
                color = White38
            )
        },
        readOnly = readOnly,
        textStyle = textStyle
    )
}

@Preview
@Composable
fun PreviewEditText() {
    EditTextFiled()
}