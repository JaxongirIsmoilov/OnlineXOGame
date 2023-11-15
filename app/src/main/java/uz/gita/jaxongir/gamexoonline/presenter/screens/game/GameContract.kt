package uz.gita.jaxongir.gamexoonline.presenter.screens.game

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon

interface GameContract {

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)

    }

    data class UiState(
        val gameUICommon: GameUICommon? = null,
        val check: Boolean = true,
        val checkWin: Boolean = false,
        val winName: String = ""
    )

    interface Intent {

        object LoadData : Intent

        object AfterLoadingData : Intent

        data class UpdateMap(
            val dataUICommon: GameUICommon,
            val newMap: String
        ) : Intent

        data class MoveToWinScreen(
            val name: String
        ) : Intent

        data class Winner(
            val name: String
        ) : Intent

    }

}