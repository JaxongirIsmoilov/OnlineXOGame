package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.gamexoonline.data.common.UserUICommon

interface UserDataStateFlowUseCase {

    val userDataStateFlow: StateFlow<List<UserUICommon>>

}