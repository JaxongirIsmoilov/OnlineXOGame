package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.AttachUsersUserCase
import javax.inject.Inject

class AttachUserUserCaseImpl @Inject constructor(
    private val repository: Repository
) : AttachUsersUserCase {
    override fun invoke(pairId: String, pairName: String): Flow<Result<Unit>> =
        repository.attachUsers(pairId, pairName)
}