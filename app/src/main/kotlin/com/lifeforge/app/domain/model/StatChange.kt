package com.lifeforge.app.domain.model

data class StatChange(
    val healthDelta: Int,
    val happinessDelta: Int,
    val fitnessDelta: Int,
    val looksDelta: Int,
    val intelligenceDelta: Int,
    val moneyDelta: Long
)