package com.facundo.mypokemonapp.framework.pokemon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {
    @Query("SELECT * FROM DbPokemon")
    fun fetchAllPokemon(): Flow<List<DbPokemon>>

    @Query("SELECT * FROM DbPokemon WHERE id = :id")
    fun findPokemonById(id: Int): Flow<DbPokemon?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: List<DbPokemon>)

    @Query("SELECT COUNT(*) FROM DbPokemon")
    suspend fun count(): Int

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: Pokemon): Int*/

    /*@Transaction
    @Query("SELECT * FROM pokemon where id = :pokemonId")
    fun getPokemonWithAbilities(pokemonId: Int): Flow<PokemonWithAbilities?>*/

}