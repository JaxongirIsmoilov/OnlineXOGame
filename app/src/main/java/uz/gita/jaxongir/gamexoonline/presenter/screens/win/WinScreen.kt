package uz.gita.jaxongir.gamexoonline.presenter.screens.win

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import uz.gita.jaxongir.gamexoonline.R
import uz.gita.jaxongir.gamexoonline.ui.components.TicTacToeScaffold

class WinScreen(
    private val name: String
) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<WinScreenViewModel>()
        WinScreenContent(name = name, viewModel)
    }
}

@Composable
fun WinScreenContent(
    name: String,
    click: WinScreenViewModel
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.winnerrr))

    TicTacToeScaffold {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LottieAnimation(
                    modifier = Modifier
                        .size(140.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                Text(
                    text = if (name == "") "DRAW" else "WINNER",
                    style = TextStyle(
                        color = Color.Yellow,
                        fontSize = 30.sp
                    )
                )
                Text(
                    text = name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 50.sp
                    )
                )


            }

            IconButton(modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomCenter)
                .wrapContentSize()
                .padding(bottom = 30.dp),
                onClick = { click.goBack() }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    tint = Color.White,
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        }
    }

}