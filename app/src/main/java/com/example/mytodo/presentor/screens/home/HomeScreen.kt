package com.example.mytodo.presentor.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.KeyEventDispatcher
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mytodo.presentor.components.Items
import com.example.mytodo.worker.WorkerHelper
import java.util.concurrent.TimeUnit

class HomeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val vm = getViewModel<HomeViewModelImpl>()
        HomeScreenContent(
            uiState = vm.uiState.collectAsState(),
            onEventDispatcher = vm::onEventDispatcher
        )
    }
}


@Composable
fun HomeScreenContent(
    uiState: State<HomeContract.UIState>,
    onEventDispatcher: (HomeContract.Intent) -> Unit
) {





    Box(modifier = Modifier.fillMaxSize()) {


        LazyColumn {
            items(uiState.value.list) {
                Items(toDoData = it)
            }
        }

        FloatingActionButton(
            onClick = { onEventDispatcher(HomeContract.Intent.MoveToAdd) },
            modifier = Modifier
                .padding(end = 25.dp, bottom = 25.dp)
                .size(60.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape),
            containerColor = Color(0xFF1285B9),
            contentColor = Color(0xFF1285B9)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = Color.White
                )
            }
        }

    }
}