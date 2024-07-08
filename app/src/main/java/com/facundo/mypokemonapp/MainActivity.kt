package com.facundo.mypokemonapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import com.facundo.mypokemonapp.ui.navigation.Navigation
import com.facundo.mypokemonapp.ui.screens.detail.DetailViewModel
import com.facundo.mypokemonapp.ui.screens.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val homeViewModel: HomeViewModel by viewModels()
    //private val detailViewModel: DetailViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

