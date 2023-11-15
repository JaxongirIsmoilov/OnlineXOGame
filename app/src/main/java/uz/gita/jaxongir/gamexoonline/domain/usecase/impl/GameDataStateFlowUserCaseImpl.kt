package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.GameDataStateFlowUserCase
import javax.inject.Inject

class GameDataStateFlowUserCaseImpl @Inject constructor(
    private val repository: Repository
) : GameDataStateFlowUserCase {
    override val gameDataStateFlow = repository.gameDataStateFlow
}