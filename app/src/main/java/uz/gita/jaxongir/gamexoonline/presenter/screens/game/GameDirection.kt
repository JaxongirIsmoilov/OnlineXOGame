package uz.gita.jaxongir.gamexoonline.presenter.screens.game

import uz.gita.jaxongir.gamexoonline.navigation.AppNavigator
import uz.gita.jaxongir.gamexoonline.presenter.screens.win.WinScreen
import javax.inject.Inject
import javax.inject.Singleton

interface GameDirection {

    suspend fun back()
    suspend fun moveToGameScreen(name: String)

}

@Singleton
class GameDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : GameDirection {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun moveToGameScreen(name: String) {
        appNavigator.replaceAll(WinScreen(name))
    }

}