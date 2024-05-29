package dev.mambo.play.myapplication.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modifier.handleKeys(block: (Key) -> Unit) = onPreInterceptKeyBeforeSoftKeyboard {
    println("Key -> ${it.key.keyCode}, ${it.type}, ${it.nativeKeyEvent}, ${it.key}")
    block(it.key)
    false
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modifier.handleKeysAfter(block: (Key, String) -> Unit,) = onInterceptKeyBeforeSoftKeyboard {
    val value = ("Key -> ${it.key.keyCode}, ${it.type}, ${it.nativeKeyEvent}, ${it.key}")
    block(it.key, value)
    false
}