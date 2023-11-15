package uz.gita.jaxongir.gamexoonline

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveAllDataUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveValueUseCase
import uz.gita.jaxongir.gamexoonline.navigation.NavigationHandler
import uz.gita.jaxongir.gamexoonline.presenter.screens.start.StartScreen
import uz.gita.jaxongir.gamexoonline.ui.theme.GameXOOnlineTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var removeValueUseCase: RemoveValueUseCase

    @Inject
    lateinit var removeAllDataUseCase: RemoveAllDataUseCase
    @SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameXOOnlineTheme {
                Navigator (
                    screen = StartScreen()
                ){navigator ->
                    navigationHandler.navigationFlow
                        .onEach {
                            it.invoke(navigator)
                        }
                        .launchIn(lifecycleScope)
                    CurrentScreen()
                }
            }
        }
    }

    override fun onStop() {
        removeValueUseCase()
        removeAllDataUseCase()
        super.onStop()
    }
}
