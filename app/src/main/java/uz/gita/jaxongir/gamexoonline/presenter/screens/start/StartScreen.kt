package uz.gita.jaxongir.gamexoonline.presenter.screens.start

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.gamexoonline.ui.components.ButtonItem
import uz.gita.jaxongir.gamexoonline.ui.components.EditTextFiled
import uz.gita.jaxongir.gamexoonline.ui.components.TicTacToeScaffold
import uz.gita.jaxongir.gamexoonline.ui.theme.Typography

class StartScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel : StartContract.ViewModel = getViewModel<StartViewModelImpl>()
        StartScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEventDispatchers = viewModel::onEventDispatches
        )
    }
}

@Composable
fun StartScreenContent(
    uiState : StartContract.UiState ,
    onEventDispatchers : (StartContract.Intent) -> Unit
) {
    TicTacToeScaffold {
        EditTextFiled(
            text = uiState.name,
            hint = "Enter your name",
            placeHolder = "Ex: Jaxongir",
            onChange = { onEventDispatchers.invoke(StartContract.Intent.ChangingName(it)) },
            textStyle = Typography.titleSmall
        )

        ButtonItem(
            text = "Enter",
            onClick = { onEventDispatchers.invoke(StartContract.Intent.MoveToUsersScreen) })
    }
}