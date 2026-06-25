package com.lifeforge.app.domain.model

data class LifeEvent(
    val title: String,
    val description: String,
    val type: LifeEventType
)

enum class LifeEventType {
    BIRTHDAY,
    EDUCATION,
    HEALTH,
    SOCIAL,
    FINANCE,
    GENERAL
}