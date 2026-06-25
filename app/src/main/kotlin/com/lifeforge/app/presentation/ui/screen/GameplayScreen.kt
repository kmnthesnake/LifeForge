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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeforge.app.R
import com.lifeforge.app.presentation.viewmodel.GameplayViewModel

@Composable
fun GameplayScreen(
    onBackClick: () -> Unit,
    viewModel: GameplayViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.character == null) {
        LaunchedEffect(Unit) {
            viewModel.startNewGame()
        }
    }

    uiState.character?.let { character ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = character.getFullName(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Age: ${character.age}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = "\$${character.money}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Character Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${character.bodyType.name} - ${character.gender.name}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${character.city}, ${character.country}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Education: ${character.educationStage.name}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Family Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Family",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Mother: ${character.motherName}",
                        fontSize = 12.sp
                    )
                    StatRow(
                        label = "Mother Bond",
                        value = character.relationshipMother,
                        delta = uiState.statChanges?.let { it.healthDelta },  // Placeholder
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Father: ${character.fatherName}",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    StatRow(
                        label = "Father Bond",
                        value = character.relationshipFather,
                        delta = uiState.statChanges?.let { it.healthDelta },  // Placeholder
                        color = MaterialTheme.colorScheme.primary
                    )
                    if (character.siblings > 0) {
                        Text(
                            text = "Siblings: ${character.siblings}",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Stats Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatRow(
                        label = "Health",
                        value = character.health,
                        delta = uiState.statChanges?.healthDelta,
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatRow(
                        label = "Happiness",
                        value = character.happiness,
                        delta = uiState.statChanges?.happinessDelta,
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatRow(
                        label = "Intelligence",
                        value = character.intelligence,
                        delta = uiState.statChanges?.intelligenceDelta,
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatRow(
                        label = "Looks",
                        value = character.looks,
                        delta = uiState.statChanges?.looksDelta,
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatRow(
                        label = "Fitness",
                        value = character.fitness,
                        delta = uiState.statChanges?.fitnessDelta,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Age +1 Button
            Button(
                onClick = { viewModel.ageUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(stringResource(id = R.string.age_button))
            }

            // Recent Events Section
            if (uiState.recentEvents.isNotEmpty()) {
                Divider()
                Text(
                    text = "Recent Events",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.recentEvents.forEach { event ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = event.title,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = event.description,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }

        // Event Dialog
        if (uiState.isEventDialogVisible && uiState.events.isNotEmpty()) {
            val currentEvent = uiState.events[uiState.currentEventIndex]
            val isLastEvent = uiState.currentEventIndex == uiState.events.size - 1

            AlertDialog(
                onDismissRequest = { viewModel.dismissEventDialog() },
                title = {
                    Text(
                        text = currentEvent.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = currentEvent.description,
                        fontSize = 14.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (isLastEvent) {
                                viewModel.dismissEventDialog()
                            } else {
                                viewModel.nextEvent()
                            }
                        }
                    ) {
                        Text(if (isLastEvent) "Done" else "Next")
                    }
                },
                dismissButton = {
                    if (!isLastEvent) {
                        TextButton(onClick = { viewModel.dismissEventDialog() }) {
                            Text("Skip All")
                        }
                    }
                }
            )
        }
    } ?: run {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading game...")
        }
    }
}

@Composable
private fun StatRow(
    label: String,
    value: Int,
    delta: Int?,
    color: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = value / 100f,
                modifier = Modifier.weight(1f),
                color = color
            )
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Text(
                    text = "$value",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (delta != null && delta != 0) {
                    Text(
                        text = if (delta > 0) "+$delta" else "$delta",
                        fontSize = 10.sp,
                        color = if (delta > 0) androidx.compose.material3.MaterialTheme.colorScheme.tertiary else androidx.compose.material3.MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
