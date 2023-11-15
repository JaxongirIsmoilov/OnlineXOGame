package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon
import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.UpdateMapUserCase
import javax.inject.Inject

class UpdateMapUserCaseImpl @Inject constructor(
    private val repository: Repository
) : UpdateMapUserCase {
    override fun invoke(gameUICommon: GameUICommon, newMap: String): Flow<Result<Unit>> =
        repository.updateData(gameUICommon, newMap)
}