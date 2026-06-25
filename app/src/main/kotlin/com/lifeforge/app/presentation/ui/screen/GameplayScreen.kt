package com.lifeforge.app.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeforge.app.R
import com.lifeforge.app.presentation.viewmodel.GameplayViewModel
import kotlinx.coroutines.launch

@Composable
fun GameplayScreen(
    onBackClick: () -> Unit,
    viewModel: GameplayViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsState(initial = null)
    val coroutineScope = rememberCoroutineScope()

    character?.let { char ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = char.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Age: ${char.age}",
                    fontSize = 16.sp
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("${stringResource(id = R.string.health)}: ${char.stats.health}/100")
                    LinearProgressIndicator(
                        progress = char.stats.health / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("${stringResource(id = R.string.fitness)}: ${char.stats.fitness}/100")
                    LinearProgressIndicator(
                        progress = char.stats.fitness / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("${stringResource(id = R.string.looks)}: ${char.stats.looks}/100")
                    LinearProgressIndicator(
                        progress = char.stats.looks / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("${stringResource(id = R.string.intelligence)}: ${char.stats.intelligence}/100")
                    LinearProgressIndicator(
                        progress = char.stats.intelligence / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "${stringResource(id = R.string.money)}: \$${char.money}",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.ageCharacter()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.age_button))
            }

            Text(
                text = "${char.country}, ${char.city}",
                fontSize = 14.sp
            )
            Text(
                text = "Body Type: ${char.bodyType.name}",
                fontSize = 14.sp
            )
        }
    } ?: run {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No character found")
        }
    }
}
