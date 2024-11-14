package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.data.PokeLocalDataSource
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject



internal class PokeRoomDataSource @Inject constructor(
    private val pokeDao: PokeDao,
    //private val pokeAbilityDao: PokeAbilityDao,
    //private val crossRefDao: PokemonAbilityCrossRefDao
) : PokeLocalDataSource {
    override val pokes: Flow<List<Pokemon>> =
        pokeDao.fetchAllPokemon().map { pokemon -> pokemon.map { it.toDomainPokemon() } }
    //val allAbilities: Flow<List<Ability>> = pokeAbilityDao.getAllAbilities()

    override fun findPokemonById(id: Int): Flow<Pokemon?> {
        return pokeDao.findPokemonById(id).map { it?.toDomainPokemon() }
    }

    override suspend fun savePokemons(pokemon: List<Pokemon>) {
        pokeDao.save(pokemon.map { it.toDbMovie() })
    }


}

private fun Pokemon.toDbMovie() = DbPokemon(
    id,
    pokemonName,
    url,
    urlImage,
    type1,
    type2,
    description,
    height,
    weight,
    base_experience,
    urlImageShiny,
    abilities,
    statusAbility,
    moves,
    isFavorite,
    region,
)

private fun DbPokemon.toDomainPokemon() = Pokemon(
    id,
    pokemonName,
    url,
    urlImage,
    type1,
    type2,
    description,
    height,
    weight,
    base_experience,
    urlImageShiny,
    abilities,
    statusAbility,
    isFavorite,
    moves,
    region
)
