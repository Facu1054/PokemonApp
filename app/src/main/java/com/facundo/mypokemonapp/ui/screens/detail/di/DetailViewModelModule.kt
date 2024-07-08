package com.facundo.mypokemonapp.ui.screens.detail.di

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.facundo.mypokemonapp.ui.navigation.NavArgs.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @PokemonId
    fun provideMovieId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle[PokeId.key] ?: -1
    }

}