package com.facundo.mypokemonapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.facundo.mypokemonapp.ui.navigation.NavArgs.*
import com.facundo.mypokemonapp.ui.screens.detail.DetailScreen
import com.facundo.mypokemonapp.ui.screens.home.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
               ) {
    val navController = rememberNavController()

    val app = LocalContext.current.applicationContext

    /*val pokeRepository = remember {
        PokeRepository(
            localDataSource = PokeLocalDataSource(),
            remoteDataSource = PokeRemoteDataSource()
        )
    }*/

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen() {pokeItem ->
                navController.navigate(NavScreen.Detail.createRoute(pokeItem.id))
                println("Pokemon ID: ${pokeItem.id}")
            }
        }
        composable(NavScreen.Detail.route,
        arguments = listOf(navArgument(PokeId.key) { type = NavType.IntType })
        ) { backstackEntry ->
            val pokemonId = requireNotNull(backstackEntry.arguments?.getInt(PokeId.key))

            DetailScreen(

                onBack = { navController.popBackStack() })
        }
    }
}

