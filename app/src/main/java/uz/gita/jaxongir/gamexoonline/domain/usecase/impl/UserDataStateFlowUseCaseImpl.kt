package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.UserDataStateFlowUseCase
import javax.inject.Inject

class UserDataStateFlowUseCaseImpl @Inject constructor(
    private val repository: Repository
) : UserDataStateFlowUseCase {
    override val userDataStateFlow = repository.userDataStateFlow
}