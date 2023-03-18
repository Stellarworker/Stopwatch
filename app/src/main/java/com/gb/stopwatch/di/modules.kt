package com.gb.stopwatch.di

import com.gb.stopwatch.view.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    viewModel { MainActivityViewModel() }
}