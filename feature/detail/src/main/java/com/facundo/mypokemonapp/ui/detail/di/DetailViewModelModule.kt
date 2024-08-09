package com.facundo.mypokemonapp.ui.detail.di

import androidx.lifecycle.SavedStateHandle
import com.facundo.mypokemonapp.ui.common.NavArgs
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
        return savedStateHandle[NavArgs.PokeId.key] ?: -1
    }

}