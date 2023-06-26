package com.zak.myleaguemainscompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zak.myleaguemainscompose.data.ChampRepository
import com.zak.myleaguemainscompose.ui.screen.detail.DetailChampionViewModel
import com.zak.myleaguemainscompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ChampRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailChampionViewModel::class.java)) {
            return DetailChampionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}