package com.facundo.mypokemonapp.data.datasource.core
/*
import android.app.Application
import androidx.room.Room
import com.facundo.mypokemonapp.data.datasource.database.PokeDatabase
import com.facundo.mypokemonapp.data.datasource.remote.NetworkModule
import com.facundo.mypokemonapp.data.datasource.remote.PokeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PokeDatabase::class.java,
        "pokemon.db"
    ).build()

    @Provides
    fun provideMoviesDao(db: PokeDatabase) = db.pokeDao()

    @Provides
    @Singleton
    fun provideMoviesService(): PokeApiClient = NetworkModule.providePokemonApiClient(NetworkModule.provideRetrofit())
}*/