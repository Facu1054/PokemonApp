package com.facundo.mypokemonapp.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class NavItem (
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
){
    val route = run{
        //baseroute/{argsq}/{args2}
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args  = navArgs.map {
        navArgument(it.key){ type = it.navType }
    }

    object Main: NavItem("main")
    object Detail: NavItem("detail", listOf(NavArg.PokeId)){
        fun createNavRoute(pokeId: Int) = "$baseRoute/$pokeId"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>){
    PokeId("pokeId",NavType.IntType)
}

