package dev.mambo.play.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

object FormFieldScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        FormFieldScreenContent {
            navigator?.pop()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormFieldScreenContent(onClickBack: () -> Unit) {

    val focusManager = LocalFocusManager.current

    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var value3 by remember { mutableStateOf("") }
    var selected1 by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(false) }
    var selected3 by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }, title = {
            Text(text = "Form")
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value1,
                onValueChange = { value1 = it },
                label = { Text("Value1") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Focusable(imeAction = ImeAction.Next, onClick = { selected1 = selected1.not() }) {
                    Checkbox(
                        modifier = Modifier,
                        checked = selected1,
                        onCheckedChange = { selected1 = it }
                    )
                }
                Text(text = "Select 1")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value2,
                onValueChange = { value2 = it },
                label = { Text("Value2") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Focusable(imeAction = ImeAction.Next, onClick = { selected2 = selected2.not() }) {
                    RadioButton(
                        modifier = Modifier,
                        selected = selected2,
                        onClick = { selected2 = selected2.not() }
                    )
                }
                Text(text = "Selected 2")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value3,
                onValueChange = { value3 = it },
                label = { Text("Value3") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Focusable(imeAction = ImeAction.Done, onClick = { selected3 = selected3.not() }) {
                    RadioButton(
                        modifier = Modifier,
                        selected = selected3,
                        onClick = { selected3 = selected3.not() }
                    )
                }
                Text(text = "Selected 3")
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Focusable(
    imeAction: ImeAction,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var focused by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onClick?.invoke()
        },
        modifier = modifier.onFocusChanged { focused = it.isFocused },
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { focusManager.clearFocus() }
        ),
        decorationBox = { _ ->
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (focused) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        }
    )
}

@Composable
fun PlayRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val focusManager = LocalFocusManager.current

    var focused by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onClick?.invoke()
        },
        modifier = Modifier.onFocusChanged { focused = it.isFocused },
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        decorationBox = { _ ->
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (focused) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.background)
            ) {
                RadioButton(
                    selected = selected,
                    onClick = onClick,
                    modifier = modifier,
                    enabled = enabled,
                    colors = colors,
                    interactionSource = interactionSource
                )
            }
        }
    )
}


