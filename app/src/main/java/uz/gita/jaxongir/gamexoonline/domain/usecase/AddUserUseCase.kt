package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.Flow


interface AddUserUseCase {

    operator fun invoke(name : String) : Flow<Result<Unit>>

}