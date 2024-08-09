package com.facundo.mypokemonapp.framework.core

import android.app.Application
import androidx.room.Room
import com.facundo.mypokemonapp.framework.pokemon.network.PokeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApiClient(retrofit: Retrofit): PokeApiClient {
        return retrofit.create(PokeApiClient::class.java)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object FrameworkCoreExtrasModule {
    @Singleton
    @Provides
    fun provideRoom(app: Application): PokeDatabase =
        Room.databaseBuilder(
            app,
            PokeDatabase::class.java,
            "pokemon.db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providePokemonDao(pokemon: PokeDatabase) = pokemon.pokeDao()
}
