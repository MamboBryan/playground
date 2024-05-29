package dev.mambo.play.myapplication.navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.mambo.play.myapplication.screens.MainScreen

@Composable
fun MainNavigation() {
    Navigator(MainScreen) { navigator ->
        SlideTransition(navigator)
    }
}