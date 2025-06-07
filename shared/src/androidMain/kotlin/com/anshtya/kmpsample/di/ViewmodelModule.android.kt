package com.anshtya.kmpsample.di

import com.anshtya.kmpsample.viewmodel.PostViewModel
import org.koin.core.module.Module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewmodelModule: Module = module {
    viewModelOf(::PostViewModel)
}