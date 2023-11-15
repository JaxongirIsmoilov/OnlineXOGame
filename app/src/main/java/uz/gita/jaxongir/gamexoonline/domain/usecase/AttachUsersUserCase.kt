package uz.gita.jaxongir.gamexoonline.domain.usecase

import kotlinx.coroutines.flow.Flow


interface AttachUsersUserCase {

    operator fun invoke(pairId : String , pairName : String) : Flow<Result<Unit>>

}