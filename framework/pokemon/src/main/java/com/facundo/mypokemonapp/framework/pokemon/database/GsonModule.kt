package com.facundo.mypokemonapp.framework.pokemon.database

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}