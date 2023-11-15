package uz.gita.jaxongir.gamexoonline.presenter.screens.users

import uz.gita.jaxongir.gamexoonline.navigation.AppNavigator
import uz.gita.jaxongir.gamexoonline.presenter.screens.game.GameScreen
import javax.inject.Inject
import javax.inject.Singleton

interface UsersDirection {

    suspend fun moveToGameScreen(name: String)

}

@Singleton
class UsersDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : UsersDirection {
    override suspend fun moveToGameScreen(name: String) {
        appNavigator.navigateTo(GameScreen(name))
    }
}