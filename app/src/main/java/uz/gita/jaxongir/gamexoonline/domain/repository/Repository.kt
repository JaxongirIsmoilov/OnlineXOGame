package uz.gita.jaxongir.gamexoonline.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon
import uz.gita.jaxongir.gamexoonline.data.common.UserUICommon

interface Repository {

    val userDataStateFlow: StateFlow<List<UserUICommon>>
    val gameDataStateFlow: StateFlow<GameUICommon?>
    val battleFlow: StateFlow<String?>

    fun addUser(name: String): Flow<Result<Unit>>
    fun attachUsers(pairId: String, pairName: String): Flow<Result<Unit>>
    fun updateData(dataUICommon: GameUICommon, newMap: String): Flow<Result<Unit>>
    fun removeValue()
    fun getName(): String
    fun removeAllData()

}