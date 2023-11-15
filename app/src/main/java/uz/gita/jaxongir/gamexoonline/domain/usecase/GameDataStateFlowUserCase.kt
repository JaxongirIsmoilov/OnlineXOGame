package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon

interface GameDataStateFlowUserCase {

    val gameDataStateFlow: StateFlow<GameUICommon?>

}