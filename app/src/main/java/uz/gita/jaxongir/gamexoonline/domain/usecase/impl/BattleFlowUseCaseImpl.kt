package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.BattleFlowUseCase
import javax.inject.Inject

class BattleFlowUseCaseImpl @Inject constructor(
    private val repository: Repository
) : BattleFlowUseCase {
    override val battleFlow = repository.battleFlow
}