package com.facundo.mypokemonapp.ui.screens.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.facundo.mypokemonapp.R
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.ui.screens.home.HomeViewModel
import com.facundo.mypokemonapp.ui.shared.Screen
import com.facundo.mypokemonapp.ui.theme.backgroundPoke

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(
    pokemonId: Int,
    detailViewModel: DetailViewModel,
    onBack: () -> Unit
) {
    val pokemonInfo by detailViewModel.pokemonValue.collectAsStateWithLifecycle()
    var state by remember {
        //heroesViewModel.getAllCharactersData(0)
        detailViewModel.onCreate(pokemonId)
        mutableStateOf(false)
    }

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = pokemonInfo.pokemonName) },
                    navigationIcon = {
                        IconButton(onClick =  onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    }
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                PokemonDetail(pokemonInfo = pokemonInfo)
            }
        }
    }

}

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
                        .fillMaxWidth().background(backgroundPoke)

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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
                Text(
                    text = "Type 1: ${pokemonInfo.type1}",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                if(pokemonInfo.type2 != "") {
                    Text(
                        text = "Type 2: ${pokemonInfo.type2}",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Text(
                    text = pokemonInfo.description, color = Color.Black,
                    maxLines = 9,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }


        }
    }
}



