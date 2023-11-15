package uz.gita.jaxongir.gamexoonline.presenter.screens.start

import kotlinx.coroutines.flow.StateFlow

interface StartContract {

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatches(intent: Intent)

    }

    data class UiState(
        val name: String = ""
    )

    interface Intent {

        data class ChangingName(
            val name: String
        ) : Intent

        object MoveToUsersScreen : Intent

    }

}
