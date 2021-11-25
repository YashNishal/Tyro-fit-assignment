package com.example.tyrofit.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tyrofit.Components.Grid
import com.example.tyrofit.Components.SidePanel
import com.example.tyrofit.Components.TopBar
import com.example.tyrofit.ViewModel.MainViewModel
import com.example.tyrofit.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalFoundationApi
@Composable
fun HomeScreen(vm: MainViewModel) {

    // for delaying content to show progress bar for at least 500ms
    val delay = remember { mutableStateOf(false) }

    // for managing sidePanel state
    val drawerState = remember { mutableStateOf(DrawerValue.Closed) }

    // for bluring mainScreen
    val mainScreenBlur = remember { mutableStateOf(false) }

    /* for disabling home screen clickables
       but for now we only have filter button clickable
       so we disable filter button
     */
    val disableHomeScreen = remember { mutableStateOf(false) }

    if (drawerState.value == DrawerValue.Open) {
        disableHomeScreen.value = true
        mainScreenBlur.value = true
    }

    if (drawerState.value == DrawerValue.Closed) {
        disableHomeScreen.value = false
        mainScreenBlur.value = false
    }


    LaunchedEffect(Unit, block = {
        vm.getData()
        delay(500)
        delay.value = true
    })

    val onFilterClick = {
        if (drawerState.value == DrawerValue.Closed) {
            drawerState.value = DrawerValue.Open
        } else {
            drawerState.value = DrawerValue.Closed
        }
    }

    val onFilterClickHomeScreen = {
        if(!disableHomeScreen.value)
            onFilterClick()
    }

    Scaffold(
        content = {
            BoxWithConstraints {

                val parentWidth = constraints.maxWidth
                val parentHeight = constraints.maxHeight

                HomeView(vm, delay, mainScreenBlur, onFilterClick = onFilterClickHomeScreen)

                if (drawerState.value == DrawerValue.Open)
                    SidePanel(parentWidth, onFilterClick)
            }
        }
    )
}


@ExperimentalFoundationApi
@Composable
fun HomeView(
    vm: MainViewModel,
    delay: MutableState<Boolean>,
    mainScreenBlur: MutableState<Boolean>,
    onFilterClick: () -> Unit
) {
    /* TODO : Blur background */
    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.padding(18.dp))
        TopBar(onFilterClick)

        if (vm.errorMessage.isEmpty() && delay.value) {
            Grid(vm)
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}