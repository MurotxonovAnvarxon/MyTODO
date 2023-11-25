package com.example.mytodo

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.mytodo.presentor.screens.home.HomeScreen
import com.example.mytodo.ui.theme.MyTODOTheme
import com.example.mytodo.utils.appNavigator.AppNavigatorHandler
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigatorHandler: AppNavigatorHandler

    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTODOTheme {

                val permissionState =
                    rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

                val lifeCicleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifeCicleOwner) {
                    val obcerver = LifecycleEventObserver { _, event ->

                        if (event == Lifecycle.Event.ON_RESUME) {
                            permissionState.launchPermissionRequest()
                        }
                    }
                    lifeCicleOwner.lifecycle.addObserver(obcerver)
                    onDispose { lifeCicleOwner.lifecycle.removeObserver(obcerver) }
                }

                Navigator(HomeScreen()) { navigator ->
                    navigatorHandler.uiNavigator.onEach {
                        it.invoke(navigator)
                    }.launchIn(lifecycleScope)
                    CurrentScreen()
                }

            }
        }
    }
}
