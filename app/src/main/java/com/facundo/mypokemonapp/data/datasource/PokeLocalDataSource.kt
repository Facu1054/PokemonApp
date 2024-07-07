package com.facundo.mypokemonapp.data.datasource

import com.facundo.mypokemonapp.data.datasource.database.PokeAbilityDao
import com.facundo.mypokemonapp.data.datasource.database.PokeDao
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeLocalDataSource @Inject constructor(private val pokeDao: PokeDao, private val pokeAbilityDao: PokeAbilityDao)
{
    val pokes: Flow<List<Pokemon>> = pokeDao.fetchAllPokemon()
    val allAbilities : Flow<List<Ability>> = pokeAbilityDao.getAllAbilities()

    fun findPokemonById(id: Int): Flow<Pokemon?> {
        return pokeDao.findPokemonById(id)
    }

    suspend fun savePokemon(pokemon: List<Pokemon>) {
        pokeDao.save(pokemon)
    }

    suspend fun saveAbility(ability: Ability) {
        pokeAbilityDao.insert(ability)
    }

}