package uz.gita.jaxongir.gamexoonline.presenter.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.jaxongir.gamexoonline.domain.usecase.AttachUsersUserCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.BattleFlowUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.GetMyNameUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.UserDataStateFlowUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModelImpl @Inject constructor(
    private val userDataStateFlowUseCase: UserDataStateFlowUseCase,
    private val attachUsersUserCase: AttachUsersUserCase,
    private val battleFlowUseCase: BattleFlowUseCase,
    private val getMyNameUseCase: GetMyNameUseCase,
    private val direction: UsersDirection
) : ViewModel(), UsersContact.ViewModel {

    init {
        battleFlowUseCase.battleFlow
            .onEach {
                it?.let {
                    direction.moveToGameScreen(getMyNameUseCase())
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadData() {
        userDataStateFlowUseCase.userDataStateFlow
            .onEach { list ->
                reduce { it.copy(list = list) }
            }
            .launchIn(viewModelScope)
    }

    private fun reduce(block: (UsersContact.UiState) -> UsersContact.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override val uiState = MutableStateFlow<UsersContact.UiState>(UsersContact.UiState())

    override fun onEventDispatchers(intent: UsersContact.Intent) {
        when (intent) {
            UsersContact.Intent.LoadData -> {
                loadData()
            }

            is UsersContact.Intent.EnteringUserData -> {
                attachUsersUserCase(intent.id, intent.name)
                    .onEach {
                        it.onSuccess {
                            direction.moveToGameScreen(getMyNameUseCase())
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

}