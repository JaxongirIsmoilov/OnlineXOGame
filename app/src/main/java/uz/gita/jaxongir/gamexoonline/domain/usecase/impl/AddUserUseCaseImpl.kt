package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.AddUserUseCase
import uz.gita.jaxongir.gamexoonline.utils.MyLog
import javax.inject.Inject

class AddUserUseCaseImpl @Inject constructor(
    private val repository: Repository
) : AddUserUseCase {

    override fun invoke(name: String): Flow<Result<Unit>> = flow {
        MyLog("usercase")
        emitAll(repository.addUser(name))
    }


}