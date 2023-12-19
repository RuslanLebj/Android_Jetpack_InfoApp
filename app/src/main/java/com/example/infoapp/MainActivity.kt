package com.example.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.infoapp.ui.theme.InfoAppTheme
import com.example.infoapp.ui_components.DrawerMenu
import com.example.infoapp.ui_components.MainTopBar
import com.example.infoapp.utils.DrawerEvents
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val topBarTitle = remember {
                mutableStateOf("")
            }
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    MainTopBar(
                        title = topBarTitle.value,
                        scaffoldState
                    )
                },
                drawerContent = {
                    DrawerMenu(){ event ->
                        when(event){
                            is DrawerEvents.OnItemClick -> {
                                topBarTitle.value = event.title
                            }
                        }
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            ) {
            }
        }
    }
}
