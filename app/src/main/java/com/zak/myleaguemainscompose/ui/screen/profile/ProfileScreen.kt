package com.zak.myleaguemainscompose.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zak.myleaguemainscompose.R
import com.zak.myleaguemainscompose.ui.theme.MyLeagueMainsComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = R.drawable.profile,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(300.dp)
                    .clip(CircleShape)
            )
        }
        Text(
            text = "Ciptagusti Sila Sakti",
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp),
            fontSize = 35.sp
        )
        Text(
            text = "A305DSX2455@bangkit.academy",
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(start = 16.dp,
                top = 8.dp),
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    MyLeagueMainsComposeTheme {
        ProfileScreen()
    }
}