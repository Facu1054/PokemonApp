package com.facundo.mypokemonapp.framework.pokemon.database

import com.facundo.mypokemonapp.domain.pokemon.data.PokeMovesConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokeMovesConverterModule {

    @Binds
    abstract fun bindPokeMovesConverter(
        impl: PokeMovesConverterImpl
    ): PokeMovesConverter
}