package com.facundo.mypokemonapp.data.datasource.database
/*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.facundo.mypokemonapp.domain.model.Ability
import com.facundo.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeAbilityDao {
    @Query("SELECT * FROM Ability WHERE id = :id")
    fun findAbility(id: Int): Flow<Ability>

    @Insert
    suspend fun saveAbility(ability: Ability): Int

    @Query("SELECT * FROM Ability")
    fun getAllAbilities(): Flow<List<Ability>>
}*/