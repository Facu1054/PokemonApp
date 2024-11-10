package com.facundo.mypokemonapp.di

import android.app.Application
import androidx.room.Room
import com.facundo.mypokemonapp.framework.core.FrameworkCoreExtrasModule
import com.facundo.mypokemonapp.framework.core.PokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FrameworkCoreExtrasModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PokeDatabase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            PokeDatabase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
        return db
    }


    @Provides
    @Named("apiUrl")
    fun provideTestApiUrl(): String = "http://localhost:8080" // URL específica para pruebas
}