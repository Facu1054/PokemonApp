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
import com.facundo.mypokemonapp.ui.common.NavArgs.*
import com.facundo.mypokemonapp.ui.detail.DetailScreen
import mypokemonapp.ui.home.HomeScreen

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

    NavHost(navController = navController, startDestination = com.facundo.mypokemonapp.ui.common.NavScreen.Home.route) {
        composable(com.facundo.mypokemonapp.ui.common.NavScreen.Home.route) {
            mypokemonapp.ui.home.HomeScreen() { pokeItem ->
                navController.navigate(com.facundo.mypokemonapp.ui.common.NavScreen.Detail.createRoute(pokeItem.id))
                println("Pokemon ID: ${pokeItem.id}")
            }
        }
        composable(
            com.facundo.mypokemonapp.ui.common.NavScreen.Detail.route,
        arguments = listOf(navArgument(PokeId.key) { type = NavType.IntType })
        ) { backstackEntry ->
            DetailScreen(

                onBack = { navController.popBackStack() })
        }
    }
}

