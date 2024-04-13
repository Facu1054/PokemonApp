package com.facundo.mypokemonapp.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.ui.shared.Screen
import com.facundo.mypokemonapp.ui.theme.backgroundPoke

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, onPokemonClick: (Pokemon) -> Unit) {
    val pokemonList by homeViewModel.pokemonValue.collectAsStateWithLifecycle()
    var state by remember {
        //heroesViewModel.getAllCharactersData(0)
        homeViewModel.onCreate()
        mutableStateOf(false)
    }


    Screen {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Pokemon") },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding ->

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = padding
            ) {

                items(pokemonList) { pokemon ->
                    PokemonItem(pokemon = pokemon, onClick = { onPokemonClick(pokemon) })
                }
            }
        }
    }

}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(modifier = Modifier.clickable {
        onClick()
    }
    ) {
        Column {

            AsyncImage(

                model = pokemon.urlImage
                    , contentDescription = pokemon.pokemonName,
                modifier = Modifier
                    .fillMaxWidth().background(backgroundPoke)
                    .aspectRatio(3 / 3f)
                    .clip(MaterialTheme.shapes.small)
            )

            Text(
                text = pokemon.pokemonName,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundPoke)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}

