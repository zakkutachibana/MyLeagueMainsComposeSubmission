package com.zak.myleaguemainscompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ChampListItem(
    name: String,
    img: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = img,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
        )
        Column {
            Modifier.weight(1f)
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
            Text(
                text = role,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}