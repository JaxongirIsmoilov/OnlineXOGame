package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon

interface UpdateMapUserCase {

    operator fun invoke(gameUICommon: GameUICommon, newMap: String): Flow<Result<Unit>>

}