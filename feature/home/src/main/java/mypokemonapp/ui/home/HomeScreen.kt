package mypokemonapp.ui.home

import android.Manifest
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.ui.common.AcScaffold
import com.facundo.mypokemonapp.ui.common.PermissionRequestEffect
import com.facundo.mypokemonapp.ui.common.Screen
import com.facundo.mypokemonapp.ui.common.theme.backgroundPoke
import com.facundo.mypokemonapp.ui.common.Result


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onPokemonClick: (Pokemon) -> Unit
) {
    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }

    val state by vm.state.collectAsState()
    HomeScreen(
        homeViewModel = state,
        onPokemonClick = onPokemonClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    homeViewModel: Result<List<Pokemon>>,
    onPokemonClick: (Pokemon) -> Unit
) {
    //val pokemonList by homeViewModel.pokemonValue.collectAsStateWithLifecycle()
    //val state by homeViewModel.state.collectAsState()
    val homeState = rememberHomeState()


    /*LaunchedEffect(key1 = true) {
        homeViewModel.onCreate()
    }*/

    Screen {
        AcScaffold(
            state = homeViewModel,
            topBar = {
                TopAppBar(
                    title = { Text(text = "Pokemon") },
                    scrollBehavior = homeState.scrollBehavior,
                    )
            },
            modifier = Modifier.nestedScroll(homeState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding, pokemon ->


            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = padding
            ) {

                items(pokemon, key = {it.id}) { pokemon ->
                    PokemonItem(pokemon = pokemon, onClick = { onPokemonClick(pokemon) })
                }
            }

        }
    }

}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable {
        onClick()
    }) {
    Card(
    ) {
            AsyncImage(

                model = pokemon.urlImage, contentDescription = pokemon.pokemonName,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundPoke)
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

