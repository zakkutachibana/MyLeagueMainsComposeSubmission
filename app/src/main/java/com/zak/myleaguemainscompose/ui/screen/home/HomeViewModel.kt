package com.zak.myleaguemainscompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zak.myleaguemainscompose.data.ChampRepository
import com.zak.myleaguemainscompose.model.Champions
import com.zak.myleaguemainscompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ChampRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Champions>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Champions>>>
        get() = _uiState


    fun getAllChampions() {
        viewModelScope.launch {
            repository.getAllChampions()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { champions ->
                    _uiState.value = UiState.Success(champions)
                }
        }
    }
}