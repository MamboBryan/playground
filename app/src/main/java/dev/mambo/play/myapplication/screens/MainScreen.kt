package dev.mambo.play.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

data class MainItem(val name: String, val screen: Screen)

object MainScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        MainScreenContent { navigator?.push(it) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(navigate: (Screen) -> Unit) {
    val list by remember { mutableStateOf(listOf(MainItem(name = "Form", screen = FormFieldScreen))) }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Playground") })
    }) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(list) {
                Card(
                    onClick = { navigate(it.screen) },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .25f),
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                        Text(modifier = Modifier.padding(16.dp).align(Alignment.BottomStart), text = it.name)
                    }
                }
            }
        }
    }
}

