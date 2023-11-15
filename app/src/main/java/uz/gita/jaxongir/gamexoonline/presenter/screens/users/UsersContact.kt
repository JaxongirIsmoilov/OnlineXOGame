package uz.gita.jaxongir.gamexoonline.presenter.screens.users

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.gamexoonline.data.common.UserUICommon

interface UsersContact {

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatchers(intent: Intent)
    }

    data class UiState(
        val list: List<UserUICommon> = listOf()
    )

    interface Intent {
        data class EnteringUserData(
            val id: String,
            val name: String
        ) : Intent

        object LoadData : Intent
    }

}