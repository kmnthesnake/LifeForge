package com.lifeforge.app.domain.model

data class SimulationResult(
    val updatedCharacter: Character,
    val events: List<LifeEvent>,
    val statChanges: StatChange
)