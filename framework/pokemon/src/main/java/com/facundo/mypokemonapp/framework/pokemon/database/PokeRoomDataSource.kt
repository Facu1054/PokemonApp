package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.data.PokeLocalDataSource
import com.facundo.mypokemonapp.domain.pokemon.model.Pokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject



class PokeRoomDataSource @Inject constructor(
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
    isFavorite,
    region
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
    region
)
