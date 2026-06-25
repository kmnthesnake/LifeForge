package com.lifeforge.app.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeforge.app.R
import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Gender
import com.lifeforge.app.presentation.viewmodel.CharacterCreationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreationScreen(
    onCharacterCreated: () -> Unit,
    viewModel: CharacterCreationViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf(Gender.MALE) }
    var selectedBodyType by remember { mutableStateOf(BodyType.AVERAGE) }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    var bodyTypeExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.character_creation))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(id = R.string.name)) },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = genderExpanded,
            onExpandedChange = { genderExpanded = !genderExpanded }
        ) {
            TextField(
                readOnly = true,
                value = selectedGender.name,
                onValueChange = { },
                label = { Text(stringResource(id = R.string.gender)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = { genderExpanded = false }
            ) {
                Gender.values().forEach { gender ->
                    DropdownMenuItem(
                        text = { Text(gender.name) },
                        onClick = {
                            selectedGender = gender
                            genderExpanded = false
                        }
                    )
                }
            }
        }

        TextField(
            value = country,
            onValueChange = { country = it },
            label = { Text(stringResource(id = R.string.country)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text(stringResource(id = R.string.city)) },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = bodyTypeExpanded,
            onExpandedChange = { bodyTypeExpanded = !bodyTypeExpanded }
        ) {
            TextField(
                readOnly = true,
                value = selectedBodyType.name,
                onValueChange = { },
                label = { Text(stringResource(id = R.string.body_type)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = bodyTypeExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = bodyTypeExpanded,
                onDismissRequest = { bodyTypeExpanded = false }
            ) {
                BodyType.values().forEach { bodyType ->
                    DropdownMenuItem(
                        text = { Text(bodyType.name) },
                        onClick = {
                            selectedBodyType = bodyType
                            bodyTypeExpanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.createCharacter(
                        name = name,
                        gender = selectedGender,
                        country = country,
                        city = city,
                        bodyType = selectedBodyType
                    )
                    onCharacterCreated()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.create))
        }
    }
}
