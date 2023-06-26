package com.zak.myleaguemainscompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.zak.myleaguemainscompose.R
import com.zak.myleaguemainscompose.di.Injection
import com.zak.myleaguemainscompose.ui.ViewModelFactory
import com.zak.myleaguemainscompose.ui.common.UiState
import com.zak.myleaguemainscompose.ui.theme.MyLeagueMainsComposeTheme

@Composable
fun DetailScreen(
    champId: Long,
    viewModel: DetailChampionViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getChampionById(champId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContents(
                    data.champ.splashImg,
                    data.champ.name,
                    data.champ.role,
                    data.champ.description,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContents(
    img: String,
    name: String,
    role: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = img,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .heightIn(min = 200.dp)
                        .fillMaxWidth()
                )
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() },
                    tint = White,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = role,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(LightGray))
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentsPreview() {
    MyLeagueMainsComposeTheme {
        DetailContents(
            "R.drawable.c_twisted_fate",
            "Tobias Felix",
            "Mid",
            "Twisted Fate is an infamous cardshark and swindler who has gambled and charmed his way across much of the known world, earning the enmity and admiration of the rich and foolish alike. He rarely takes things seriously, greeting each day with a mocking smile and an insouciant swagger. In every possible way, Twisted Fate always has an ace up his sleeve.",
            onBackClick = {}
        )
    }
}