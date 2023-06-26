package com.zak.myleaguemainscompose.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zak.myleaguemainscompose.di.Injection
import com.zak.myleaguemainscompose.model.Champions
import com.zak.myleaguemainscompose.ui.ViewModelFactory
import com.zak.myleaguemainscompose.ui.common.UiState
import com.zak.myleaguemainscompose.ui.components.ChampListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllChampions()
            }
            is UiState.Success -> {
                HomeContent(
                    champs = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    champs: List<Champions>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("ChampList")
    ) {
        items(champs) { data ->
            ChampListItem(
                name = data.champ.name,
                img = data.champ.squareImg,
                role = data.champ.role,
                modifier = Modifier.clickable {
                    Log.d("HOMESCREEN", "This is${data.champ.name}")
                    navigateToDetail(data.champ.id)
                }
            )
        }
    }
}

