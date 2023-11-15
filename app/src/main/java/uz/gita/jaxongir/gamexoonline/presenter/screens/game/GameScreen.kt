package uz.gita.jaxongir.gamexoonline.presenter.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.gamexoonline.R
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon
import uz.gita.jaxongir.gamexoonline.utils.setWinner

class GameScreen(
    private val name : String
) : AndroidScreen(){
    @Composable
    override fun Content() {
        val viewModel : GameContract.ViewModel = getViewModel<GameViewModelImpl>()
        viewModel.onEventDispatchers(GameContract.Intent.LoadData)
        GameScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEventDispatchers = viewModel::onEventDispatchers ,
            name
        )
    }

}


@Composable
fun GameScreenContent(
    uiState: GameContract.UiState ,
    onEventDispatchers : (GameContract.Intent) -> Unit ,
    name : String
) {
    if (uiState.checkWin) onEventDispatchers(GameContract.Intent.MoveToWinScreen(uiState.winName))
    if (uiState.gameUICommon != null) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ){
            val ls = uiState.gameUICommon.map.toCharArray()
            Column (
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
                    .height(320.dp)
                    .align(Alignment.Center)
            ){
                for (i in 0 until 3) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        for (j in 0 until 3) {
                            Box (
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                Color(0xFF3F51B5),
                                                Color(0xFF253694)
                                            )
                                        )
                                    )
                                    .clickable {
                                        if (isWay((i * 3 + j).toChar() , uiState.gameUICommon.indexHod.toCharArray()) && uiState.gameUICommon.map.length != uiState.gameUICommon.indexHod.length) {
                                            if (uiState.gameUICommon.firstName == name && uiState.gameUICommon.firstHod) {
                                                val value = uiState.gameUICommon.firstSign
                                                var indexHod = uiState.gameUICommon.indexHod
                                                indexHod += (i * 3 + j).toString()
                                                val newGameCommon = GameUICommon(
                                                    gameId = uiState.gameUICommon.gameId,
                                                    uiState.gameUICommon.firstId,
                                                    uiState.gameUICommon.firstName,
                                                    !uiState.gameUICommon.firstHod,
                                                    uiState.gameUICommon.firstSign,
                                                    uiState.gameUICommon.secondId,
                                                    uiState.gameUICommon.secondName,
                                                    !uiState.gameUICommon.secondHod,
                                                    uiState.gameUICommon.secondSign,
                                                    uiState.gameUICommon.winner ,
                                                    uiState.gameUICommon.winnerName ,
                                                    indexHod ,
                                                    uiState.gameUICommon.map,
                                                    uiState.gameUICommon.hasWay
                                                )
                                                val newMap = StringBuilder(uiState.gameUICommon.map)
                                                    .replace(i * 3 + j, i * 3 + j + 1, value)
                                                    .toString()
                                                onEventDispatchers(
                                                    GameContract.Intent.UpdateMap(
                                                        newGameCommon,
                                                        newMap
                                                    )
                                                )
                                                if (isXWin(newMap.toCharArray())) {
                                                    onEventDispatchers(
                                                        GameContract.Intent.UpdateMap(
                                                            newGameCommon.setWinner(newGameCommon.firstName),
                                                            newMap
                                                        )
                                                    )
                                                    onEventDispatchers(
                                                        GameContract.Intent.Winner(newGameCommon.firstName)
                                                    )

                                                }
                                            } else if (uiState.gameUICommon.secondName == name && uiState.gameUICommon.secondHod) {
                                                val value = uiState.gameUICommon.secondSign
                                                var indexHod = uiState.gameUICommon.indexHod
                                                indexHod += (i * 3 + j).toString()
                                                val newGameCommon = GameUICommon(
                                                    gameId = uiState.gameUICommon.gameId,
                                                    uiState.gameUICommon.firstId,
                                                    uiState.gameUICommon.firstName,
                                                    !uiState.gameUICommon.firstHod,
                                                    uiState.gameUICommon.firstSign,
                                                    uiState.gameUICommon.secondId,
                                                    uiState.gameUICommon.secondName,
                                                    !uiState.gameUICommon.secondHod,
                                                    uiState.gameUICommon.secondSign,
                                                    uiState.gameUICommon.winner ,
                                                    uiState.gameUICommon.winnerName ,
                                                    indexHod ,
                                                    uiState.gameUICommon.map,
                                                    uiState.gameUICommon.hasWay
                                                )
                                                val newMap = StringBuilder(uiState.gameUICommon.map)
                                                    .replace(i * 3 + j, i * 3 + j + 1, value)
                                                    .toString()
                                                onEventDispatchers(
                                                    GameContract.Intent.UpdateMap(
                                                        newGameCommon,
                                                        newMap
                                                    )
                                                )
                                                if (isOWin(newMap.toCharArray())) {
                                                    onEventDispatchers(
                                                        GameContract.Intent.UpdateMap(
                                                            newGameCommon.setWinner(newGameCommon.secondName),
                                                            newMap
                                                        )
                                                    )
                                                    onEventDispatchers(
                                                        GameContract.Intent.Winner(newGameCommon.secondName)
                                                    )
                                                }
                                            }
                                        } else if (uiState.gameUICommon.map.length == uiState.gameUICommon.indexHod.length){
                                            onEventDispatchers(
                                                GameContract.Intent.UpdateMap(
                                                    uiState.gameUICommon.setWinner(""),
                                                    uiState.gameUICommon.map
                                                )
                                            )
                                            onEventDispatchers(
                                                GameContract.Intent.Winner("")
                                            )
                                        }

                                    }
                            ){
                                if (ls[i * 3 + j] != '1') {
                                    Image(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .align(Alignment.Center),
                                        painter = painterResource(id =
                                        if(ls[i * 3 + j] == '0')
                                            R.drawable.x_icon
                                        else
                                            R.drawable.o_icon),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 5.dp)
            ){
                Row (
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ){
                    Image(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .size(40.dp),
                        painter = painterResource(id = R.drawable.gamer),
                        contentDescription = ""
                    )
                    Column {
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp) ,
                                text = "Sign : " ,
                                style = TextStyle(
                                    color = Color.White ,
                                    fontFamily = FontFamily(listOf(Font(R.font.irish_grover_regular))) ,
                                    fontSize = 18.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp) ,
                                painter = painterResource(id = R.drawable.x_icon),
                                contentDescription = "" ,
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp) ,
                            text = uiState.gameUICommon.firstName ,
                            style = TextStyle(
                                color = Color.White ,
                                fontFamily = FontFamily(listOf(Font(R.font.irish_grover_regular))) ,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.swords),
                    contentDescription = ""
                )
                Row (
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ){

                    Column {
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                modifier = Modifier.size(15.dp) ,
                                painter = painterResource(id = R.drawable.o_icon),
                                contentDescription = "" ,
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(end = 5.dp) ,
                                text = " : Sign" ,
                                style = TextStyle(
                                    color = Color.White ,
                                    fontFamily = FontFamily(listOf(Font(R.font.irish_grover_regular))) ,
                                    fontSize = 18.sp
                                )
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp) ,
                            text = uiState.gameUICommon.secondName ,
                            style = TextStyle(
                                color = Color.White ,
                                fontFamily = FontFamily(listOf(Font(R.font.irish_grover_regular))) ,
                                fontSize = 16.sp ,
                                textAlign = TextAlign.End
                            )
                        )
                    }
                    Image(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(40.dp),
                        painter = painterResource(id = R.drawable.gammer2),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


fun isWay(index : Char , way : CharArray) : Boolean{
    for (i in way.indices) {
        if (index == way[i]) return false
    }
    return true
}

fun isXWin(map : CharArray) : Boolean {
    val satr0 = map[0] == '0' && map[1] == map[0] && map[1] == map[2];
    val satr1 = map[3] == '0' && map[4] == map[3] && map[5] == map[3];
    val satr2 = map[6] == '0' && map[6] == map[7] && map[6] == map[8];

    val satr = satr0 || satr1 ||  satr2;

    val ustun0 = map[0] == '0' && map[3] == map[0] && map[3] == map[6];
    val ustun1 = map[1] == '0' && map[1] == map[4] && map[1] == map[7];
    val ustub2 = map[2] == '0' && map[2] == map[5] && map[2] == map[8];

    val ustun = ustun0 || ustun1 || ustub2

    val diagonal1 = map[0] == '0' && map[0] == map[4] && map[0] == map[8];
    val diagonal2 = map[2] == '0' && map[2] == map[4] && map[2] == map[6];

    val diognal = diagonal1 ||  diagonal2;

    return satr || ustun || diognal;
}

fun isOWin(map : CharArray) : Boolean {
    val satr0 = map[0] == '2' && map[1] == map[0] && map[1] == map[2];
    val satr1 = map[3] == '2' && map[4] == map[3] && map[5] == map[3];
    val satr2 = map[6] == '2' && map[6] == map[7] && map[6] == map[8];

    val satr = satr0 || satr1 ||  satr2;

    val ustun0 = map[0] == '2' && map[3] == map[0] && map[3] == map[6];
    val ustun1 = map[1] == '2' && map[1] == map[4] && map[1] == map[7];
    val ustub2 = map[2] == '2' && map[2] == map[5] && map[2] == map[8];

    val ustun = ustun0 || ustun1 || ustub2

    val diagonal1 = map[0] == '2' && map[0] == map[4] && map[0] == map[8];
    val diagonal2 = map[2] == '2' && map[2] == map[4] && map[2] == map[6];

    val diognal = diagonal1 ||  diagonal2;

    return satr || ustun || diognal;
}

@Preview(showBackground = true)
@Composable
fun GameScreenContentPreview() {
    GameScreenContent(
        GameContract.UiState() , {} ,"")
}