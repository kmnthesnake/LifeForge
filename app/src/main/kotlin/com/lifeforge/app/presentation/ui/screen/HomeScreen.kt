package com.lifeforge.app.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeforge.app.R

@Composable
fun HomeScreen(
    onNewLifeClick: () -> Unit,
    onContinueClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onExitClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            onClick = onNewLifeClick,
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.new_life))
        }

        Button(
            onClick = onContinueClick,
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.continue_game))
        }

        Button(
            onClick = onSettingsClick,
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.settings))
        }

        Button(
            onClick = onExitClick,
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
        ) {
            Text("Exit")
        }
    }
}