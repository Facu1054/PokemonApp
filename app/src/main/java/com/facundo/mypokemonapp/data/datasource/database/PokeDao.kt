package com.facundo.mypokemonapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {
    @Query("SELECT * FROM Pokemon")
    fun fetchAllPokemon(): Flow<List<Pokemon>>

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    fun findPokemonById(id: Int): Flow<Pokemon?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: List<Pokemon>)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: Pokemon): Int*/

    /*@Transaction
    @Query("SELECT * FROM pokemon where id = :pokemonId")
    fun getPokemonWithAbilities(pokemonId: Int): Flow<PokemonWithAbilities?>*/

}