package uz.gita.jaxongir.gamexoonline.presenter.screens.start

import uz.gita.jaxongir.gamexoonline.navigation.AppNavigator
import uz.gita.jaxongir.gamexoonline.presenter.screens.users.UsersScreen
import javax.inject.Inject
import javax.inject.Singleton

interface StartDirection {

    suspend fun moveToUsersScreen()

}

@Singleton
class StartDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : StartDirection {

    override suspend fun moveToUsersScreen() {
        appNavigator.navigateTo(UsersScreen())
    }

}