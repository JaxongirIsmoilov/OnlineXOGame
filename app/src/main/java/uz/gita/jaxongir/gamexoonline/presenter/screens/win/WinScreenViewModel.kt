package uz.gita.jaxongir.gamexoonline.presenter.screens.win

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.jaxongir.gamexoonline.navigation.AppNavigator
import uz.gita.jaxongir.gamexoonline.presenter.screens.start.StartScreen
import javax.inject.Inject

@HiltViewModel
class WinScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun goBack() {
        viewModelScope.launch {
            appNavigator.replaceAll(StartScreen())
        }
    }

}