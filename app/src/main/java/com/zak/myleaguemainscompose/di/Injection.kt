package com.zak.myleaguemainscompose.di

import com.zak.myleaguemainscompose.data.ChampRepository


object Injection {
    fun provideRepository(): ChampRepository {
        return ChampRepository.getInstance()
    }
}