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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun providePokemonService(
        @Named("apiUrl") apiUrl: String
    ) : PokeApiClient = PokemonClient(apiUrl).instance

    @Provides
    fun providePokemonDao(db: PokeDatabase) = db.pokeDao()

    /*@Provides
    @Singleton
    fun provideMoviesService(): PokeApiClient = PokemonClient().instance*/



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


    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "https://pokeapi.co/"
}
