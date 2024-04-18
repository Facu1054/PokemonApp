package com.facundo.mypokemonapp.ui.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.ui.theme.backgroundPoke

@Composable
fun PokemonDetail(pokemonInfo: Pokemon) {
    Column {
        Row {
            Card(
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .background(Color.Transparent)
                    .padding(8.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            pokemonInfo.urlImage
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image Marvel Character",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundPoke)

                        .clip(MaterialTheme.shapes.small),
                )
            }



            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Transparent)
            ) {
                Text(
                    text = pokemonInfo.pokemonName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Row {
                    PokeFormat("Type 1: ", pokemonInfo.type1)
                }
                Row {
                    if (pokemonInfo.type2 != "") {

                        PokeFormat("Type 2: ", pokemonInfo.type2)
                    }
                }

                Row {
                    PokeFormat("Height: ", pokemonInfo.height)
                }
                Row {
                    PokeFormat("Weight: ", pokemonInfo.weight)
                }

                PokeFormat(description = "Base Experience: ", type = pokemonInfo.base_experience)
            }


        }

        Row {
            Card(
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .background(Color.Transparent)
                    .padding(8.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            pokemonInfo.urlImageShiny
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image Pokemon shiny",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)

                        .clip(MaterialTheme.shapes.small),
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Transparent)
            ) {
                Text(
                    text = "Abilities",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )

                PokeFormat("Ability 1: ", pokemonInfo.ability1.name)


                if (pokemonInfo.ability2.name != "") {
                    PokeFormat(
                        "Ability 2: ",
                        pokemonInfo.ability2.name,
                        pokemonInfo.ability2.is_hidden
                    )
                }

                if (pokemonInfo.ability3.name != "") {

                    PokeFormat(
                        "Ability 3: ",
                        pokemonInfo.ability3.name,
                        pokemonInfo.ability3.is_hidden
                    )

                }
            }

        }


    }
}

@Composable
fun PokeFormat(description: String, type: String, isHidden: Boolean = false) {
    Text(
        text = description,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Row {
        Text(
            text = type,
            fontSize = 16.sp,
            color = Color.Black
        )

        if (isHidden) {
            Text(
                text = "(Hidden Ability)",
                fontSize = 8.sp,
                color = Color.Black
            )

        }
    }
}
