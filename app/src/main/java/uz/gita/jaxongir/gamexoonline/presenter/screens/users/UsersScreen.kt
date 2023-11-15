package uz.gita.jaxongir.gamexoonline.presenter.screens.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.gamexoonline.ui.components.UserItem

class UsersScreen : AndroidScreen(){
    @Composable
    override fun Content() {
        val viewModel : UsersContact.ViewModel = getViewModel<UsersViewModelImpl>()
        viewModel.onEventDispatchers(UsersContact.Intent.LoadData)
        UsersScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEventDispatcher = viewModel::onEventDispatchers
        )
    }

}

@Composable
fun UsersScreenContent(
    uiState : UsersContact.UiState ,
    onEventDispatcher : (UsersContact.Intent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {


        if (uiState.list.isEmpty()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No Players Online", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFE3E3E3),
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Please come again later", style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF808080),
                        textAlign = TextAlign.Center
                    )
                )
            }
        } else {

            Column(modifier = Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(uiState.list) {
                        UserItem(userUICommon = it) { id, name ->
                            onEventDispatcher.invoke(
                                UsersContact.Intent.EnteringUserData(
                                    id,
                                    name
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                    }
                }
            }
        }

    }
}
