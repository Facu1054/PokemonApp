package com.facundo.mypokemonapp.data.datasource

import com.facundo.mypokemonapp.data.datasource.database.PokeDao
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeLocalDataSource @Inject constructor(
    private val pokeDao: PokeDao,
    //private val pokeAbilityDao: PokeAbilityDao,
    //private val crossRefDao: PokemonAbilityCrossRefDao
) {
    val pokes: Flow<List<Pokemon>> = pokeDao.fetchAllPokemon()
    //val allAbilities: Flow<List<Ability>> = pokeAbilityDao.getAllAbilities()

    fun findPokemonById(id: Int): Flow<Pokemon?> {
        return pokeDao.findPokemonById(id)
    }

    suspend fun savePokemons(pokemon: List<Pokemon>) {
        pokeDao.save(pokemon)
    }

/*
    suspend fun savePokemon(pokemon: Pokemon) {
        pokeDao.savePokemon(pokemon)
    }*/

    /*suspend fun saveAbilities(abilities: Ability) {
        pokeAbilityDao.saveAbility(abilities)
    }*/

/*
    suspend fun insertPokemonWithAbilities(pokemonList: List<Pokemon>) {

        //pokemon: Pokemon, abilities: List<Ability>

        pokemonList.forEach { pokemon ->
            val pokemonId = pokeDao.savePokemon(pokemon)
            pokemon.abilities.forEach { ability ->
                val abilityId = pokeAbilityDao.saveAbility(ability)
                crossRefDao.insert(PokemonAbilityCrossRef(pokemonId, abilityId))
            }
        }

    }*/

    /*fun getPokemonWithAbilities(pokemonId: Int): Flow<PokemonAbilityCrossRef?> {
        return pokeDao.getPokemonWithAbilities(pokemonId)
    }*/

}