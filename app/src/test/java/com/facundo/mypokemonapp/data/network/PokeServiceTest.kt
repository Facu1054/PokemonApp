package com.facundo.mypokemonapp.data.network

import com.facundo.mypokemonapp.framework.pokemon.network.PokeService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test


class PokeServiceTest {
    val expectedPokemonInfo =
        com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.PokemonInfoDTO(
            abilities = listOf(
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Ability(
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.AbilityX(
                        "overgrow",
                        "https://pokeapi.co/api/v2/ability/65/"
                    ), false, 1
                ),
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Ability(
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.AbilityX(
                        "chlorophyll",
                        "https://pokeapi.co/api/v2/ability/34/"
                    ), true, 2
                )
            ),
            base_experience = 69,
            forms = mockk(),
            height = 7,
            held_items = mockk(),
            id = 1,
            is_default = true,
            location_area_encounters = "",
            moves = listOf(
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Move(
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.MoveX(
                        "razor-wind",
                        "https://pokeapi.co/api/v2/move/13/"
                    ), mockk()
                ),
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Move(
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.MoveX(
                        "swords-dance",
                        "https://pokeapi.co/api/v2/move/14/"
                    ), mockk()
                )
            ),
            name = "bulbasaur",
            order = 1,
            species = mockk(),
            sprites = com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Sprites(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            ),
            stats =
            listOf(
                mockk(),
                mockk(),
                mockk(),
                mockk(),
                mockk(),
                mockk()
            ),
            types = listOf(
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Type(
                    1,
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.TypeX(
                        "grass",
                        ""
                    )
                ),
                com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.Type(
                    2,
                    com.facundo.mypokemonapp.framework.pokemon.network.model.pokemonInfo.TypeX(
                        "poison",
                        ""
                    )
                )
            ),
            weight = 50
        )

    @Test
    fun `when service is called then return list of pokemon`() {
        val expectedPokemonList = listOf(
            com.facundo.mypokemonapp.framework.pokemon.network.model.Result(
                "bulbasaur",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            com.facundo.mypokemonapp.framework.pokemon.network.model.Result(
                "ivysaur",
                "https://pokeapi.co/api/v2/pokemon/2/"
            )
        )
        val apiClient = mockk<com.facundo.mypokemonapp.framework.pokemon.network.PokeApiClient> {
            coEvery { getAllPokemon().body()?.results } returns expectedPokemonList
        }

        val pokeService = PokeService(apiClient)
        val result = runBlocking { pokeService.getAllPokemon() }

        assertEquals(expectedPokemonList, result)
    }

    @Test
    fun `when service is called then return pokemon info`() {

        val apiClient = mockk<com.facundo.mypokemonapp.framework.pokemon.network.PokeApiClient> {
            coEvery { getPokemon(1).body() } returns expectedPokemonInfo
        }

        val pokeService = PokeService(apiClient)
        val result = runBlocking { pokeService.getPokemon(1) }

        assertEquals(expectedPokemonInfo, result)
    }
}