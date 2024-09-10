package com.facundo.mypokemonapp.framework.pokemon


import com.facundo.mypokemonapp.domain.pokemon.data.PokeLocalDataSource
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRemoteDataSource
import com.facundo.mypokemonapp.framework.pokemon.database.PokeRoomDataSource
import com.facundo.mypokemonapp.framework.pokemon.network.PokeServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkPokemonModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: PokeRoomDataSource): PokeLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: PokeServerDataSource): PokeRemoteDataSource

}