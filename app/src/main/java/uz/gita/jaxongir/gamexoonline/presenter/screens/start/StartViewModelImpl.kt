package uz.gita.jaxongir.gamexoonline.presenter.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.jaxongir.gamexoonline.domain.usecase.AddUserUseCase
import javax.inject.Inject

@HiltViewModel
class StartViewModelImpl @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val direction: StartDirection
) : StartContract.ViewModel, ViewModel() {

    private var name = ""
    override val uiState = MutableStateFlow<StartContract.UiState>(StartContract.UiState())

    private fun reduce(block: (StartContract.UiState) -> StartContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatches(intent: StartContract.Intent) {
        when (intent) {
            is StartContract.Intent.ChangingName -> {
                name = intent.name
                reduce { it.copy(name = name) }
            }

            StartContract.Intent.MoveToUsersScreen -> {
                addUserUseCase.invoke(name).onEach {
                    it.onSuccess {
                        direction.moveToUsersScreen()
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
