package com.zak.myleaguemainscompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zak.myleaguemainscompose.data.ChampRepository
import com.zak.myleaguemainscompose.model.Champions
import com.zak.myleaguemainscompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailChampionViewModel(
    private val repository: ChampRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Champions>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Champions>>
        get() = _uiState


    fun getChampionById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getChampionById(id))
        }
    }
}