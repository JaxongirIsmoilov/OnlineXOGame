package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.StateFlow

interface BattleFlowUseCase {

    val battleFlow : StateFlow<String?>

}