package com.facundo.mypokemonapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import com.facundo.mypokemonapp.domain.model.relations.PokemonAbilityCrossRef
import com.facundo.mypokemonapp.domain.model.relations.PokemonWithAbilities
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {
    @Query("SELECT * FROM Pokemon")
    fun fetchAllPokemon(): Flow<List<Pokemon>>

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    fun findPokemonById(id: Int): Flow<Pokemon?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(ability: Ability): Long

    @Insert
    suspend fun insert(crossRef: PokemonAbilityCrossRef)

    @Transaction
    @Query("SELECT * FROM pokemon")
    fun getPokemonWithAbilities(): Flow<List<PokemonWithAbilities>>

}