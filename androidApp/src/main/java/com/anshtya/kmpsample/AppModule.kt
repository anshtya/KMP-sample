package com.anshtya.kmpsample

import com.anshtya.kmpsample.viewmodel.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::PostViewModel)

}