package com.facundo.mypokemonapp.ui.screens.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.facundo.mypokemonapp.R
import com.facundo.mypokemonapp.ui.shared.Screen
import com.facundo.mypokemonapp.ui.theme.PokeTitle
import com.facundo.mypokemonapp.ui.theme.colorMoves

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(
    pokemonId: Int,
    detailViewModel: DetailViewModel,
    onBack: () -> Unit
) {
    //val pokemonInfo by detailViewModel.pokemonValue.collectAsStateWithLifecycle()
    val state = detailViewModel.state

    LaunchedEffect(key1 = true) {
        detailViewModel.onCreate(pokemonId)
    }

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(
                        text = state.pokemon.pokemonName,
                        fontWeight = FontWeight.Bold
                    ) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PokeTitle,
                    )
                )
            },
        ) { padding ->

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(padding),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

            } else {
                Column {
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        PokemonDetail(pokemonInfo = state.pokemon)
                    }

                    Card(
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

                            items(state.pokemon.moves) { move ->

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
            }
        }
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



