package com.facundo.mypokemonapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.facundo.mypokemonapp.ui.screens.detail.DetailScreen
import com.facundo.mypokemonapp.ui.screens.detail.DetailViewModel
import com.facundo.mypokemonapp.ui.screens.home.HomeScreen
import com.facundo.mypokemonapp.ui.screens.home.HomeViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(homeViewModel: HomeViewModel,
               detailViewModel: DetailViewModel
               ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.Main.route) {
        composable(NavItem.Main) {
            HomeScreen(homeViewModel = homeViewModel) {pokeItem ->
                navController.navigate(NavItem.Detail.createNavRoute(pokeItem.id))
            }
        }
        composable(NavItem.Detail)
        { backstackEntry ->

            DetailScreen(pokemonId = backstackEntry.findArg(arg = NavArg.PokeId),
                detailViewModel = detailViewModel,
                onBack = { navController.popBackStack() })
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
){
    composable(
        route = navItem.route,
        arguments = navItem.args
    ){
        content(it)
    }
}

private inline fun < reified T> NavBackStackEntry.findArg(arg: NavArg): T{
    val value = arguments?.get(arg.key)
    //val value = arguments?.getByteArray(arg.key)
    requireNotNull(id, { "Pokemon ID is required" })
    return  value as T
}