package com.facundo.mypokemonapp.data.datasource.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): PokeDatabase =
        Room.databaseBuilder(
            context,
            PokeDatabase::class.java,
            "pokemon.db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providePokemonDao(pokemon: PokeDatabase) = pokemon.pokeDao()
}