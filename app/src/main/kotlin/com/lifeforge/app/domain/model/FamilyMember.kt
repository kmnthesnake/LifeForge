package com.lifeforge.app.domain.model

data class FamilyMember(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val relationship: Int = 50
)