package uz.gita.jaxongir.gamexoonline.presenter.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.jaxongir.gamexoonline.domain.usecase.GameDataStateFlowUserCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveAllDataUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveValueUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.UpdateMapUserCase
import uz.gita.jaxongir.gamexoonline.utils.MyLog
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val gameDataStateFlowUserCase: GameDataStateFlowUserCase,
    private val updateMapUserCase: UpdateMapUserCase,
    private val removeAllDataUseCase: RemoveAllDataUseCase,
    private val removeValueUseCase: RemoveValueUseCase,
    private val direction: GameDirection
) : GameContract.ViewModel, ViewModel() {


    private fun loadData() {

        MyLog("LoadData")

        gameDataStateFlowUserCase.gameDataStateFlow
            .onEach {
                it?.let { data ->
                    if (it.winner) {
                        removeAllDataUseCase()
                        removeValueUseCase()
                        direction.moveToGameScreen(it.winnerName)
                    }
                    reduce { it.copy(gameUICommon = data) }
                }
            }
            .launchIn(viewModelScope)

    }

    var name = ""


    private fun reduce(block: (GameContract.UiState) -> GameContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override val uiState = MutableStateFlow<GameContract.UiState>(GameContract.UiState())

    override fun onEventDispatchers(intent: GameContract.Intent) {

        when (intent) {

            GameContract.Intent.AfterLoadingData -> {
                reduce { it.copy(check = false) }
            }

            GameContract.Intent.LoadData -> {
                loadData()
            }

            is GameContract.Intent.UpdateMap -> {
                MyLog("onEventDispatchers : UpdateMap")
                updateMapUserCase(intent.dataUICommon, intent.newMap)
                    .onEach {
                        it.onSuccess {
                        }
                    }
                    .launchIn(viewModelScope)
            }

            is GameContract.Intent.MoveToWinScreen -> {
                viewModelScope.launch {
                    name = intent.name
                    removeAllDataUseCase()
                    removeValueUseCase()
                    direction.moveToGameScreen(name)
                }
            }

            is GameContract.Intent.Winner -> {
                reduce { it.copy(winName = intent.name) }
                reduce { it.copy(checkWin = true) }
            }

        }

    }
}