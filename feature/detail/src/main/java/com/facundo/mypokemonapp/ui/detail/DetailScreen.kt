package com.facundo.mypokemonapp.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon
import com.facundo.mypokemonapp.ui.common.AcScaffold
import com.facundo.mypokemonapp.ui.common.R.string
import com.facundo.mypokemonapp.ui.common.Screen
import com.facundo.mypokemonapp.ui.common.theme.PokeTitle

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(

    detailViewModel: DetailViewModel = hiltViewModel(),
    //pokeId : Int,
    onBack: () -> Unit
) {
    //val pokemonInfo by detailViewModel.pokemonValue.collectAsStateWithLifecycle()
    val state by detailViewModel.state.collectAsState()
    val detailState = rememberDetailState(state)

    /*state.message?.let {
        detailState.ShowMessageEffect(message = it) {
            detailViewModel.onAction(DetailAction.MessageShown)
        }
    }*/

    /*LaunchedEffect(key1 = true) {
        detailViewModel.onCreate(pokemon)
        //println(detailState.pokemon?.pokemonName)
    }*/

    Screen {
        AcScaffold(
            state = state,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = detailState.pokemon?.pokemonName ?: "",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = string.back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PokeTitle,
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    detailViewModel.onFavoriteClicked()
                    //detailViewModel.onAction(DetailAction.FavoriteClick)
                    }) {
                    val favorite = detailState.pokemon?.isFavorite ?: false
                    Icon(
                        imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(
                            id = string.favorite
                        )
                    )
                }
            },
            snackbarHost = { SnackbarHost(hostState = detailState.snackbarHostState) },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding, pokemon ->

            PokemonTemplate(pokemon = pokemon, padding = padding,detailViewModel = detailViewModel)
            /*detailState.pokemon?.let { pokemon ->
                PokemonTemplate(pokemon = pokemon, padding = padding)
            }*/

        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PokemonTemplate(pokemon: Pokemon, padding: PaddingValues, detailViewModel: DetailViewModel) {
    detailViewModel.onCreate(pokemon)
    Column {
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            pokemon?.let { PokemonDetail(pokemonInfo = it, detailViewModel = detailViewModel) }
        }

        /*Card(
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .background(Color.Transparent)
                .padding(8.dp)
        ) {


            LazyColumn(

            ) {

                item {

                    Row(Modifier.background(colorMoves)) {
                        TableCell(text = "Move", weight = 1f, color = Color.White)
                        TableCell(text = "Learn method", weight = 1f, color = Color.White)
                    }
                }

                pokemon?.let {
                    items(it.moves) { move ->

                        Row(modifier = Modifier.fillMaxWidth()) {
                            TableCell(
                                text = move.move.name,
                                weight = 1f
                            )
                            val learnMethod =
                                move.version_group_details.last().move_learn_method.name
                            TableCell(
                                text = learnMethod,
                                weight = 1f,
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
        */
    }
}


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    color: Color = Color.Black,
    icon: ImageVector? = null
) {
    if (icon != null) {
        Icon(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .weight(.2f)
                .padding(8.dp),
            imageVector = icon,
            contentDescription = "Learning method"
        )
    }

    Text(
        text = text,
        color = color,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}



