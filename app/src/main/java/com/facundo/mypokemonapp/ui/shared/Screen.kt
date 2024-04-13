package com.facundo.mypokemonapp.ui.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.facundo.mypokemonapp.ui.theme.MyPokemonAppTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    MyPokemonAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )

    }
}