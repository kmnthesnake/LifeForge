package com.lifeforge.app.engine

import com.lifeforge.app.domain.model.FamilyMember
import com.lifeforge.app.domain.model.Gender
import kotlin.random.Random

object FamilyGenerator {

    private val maleFirstNames = listOf(
        "James", "Robert", "Michael", "William", "David",
        "Richard", "Joseph", "Thomas", "Charles", "Christopher",
        "Daniel", "Matthew", "Anthony", "Mark", "Donald",
        "Steven", "Paul", "Andrew", "Joshua", "Kenneth"
    )

    private val femaleFirstNames = listOf(
        "Mary", "Patricia", "Jennifer", "Linda", "Barbara",
        "Elizabeth", "Susan", "Jessica", "Sarah", "Karen",
        "Nancy", "Betty", "Margaret", "Sandra", "Ashley",
        "Kimberly", "Emily", "Donna", "Michelle", "Dorothy"
    )

    fun generateMother(lastName: String): String {
        return "${femaleFirstNames.random()} $lastName"
    }

    fun generateFather(lastName: String): String {
        return "${maleFirstNames.random()} $lastName"
    }

    fun generateSiblings(playerLastName: String, playerAge: Int): List<FamilyMember> {
        val siblingCount = Random.nextInt(0, 5)  // 0-4 siblings
        val siblings = mutableListOf<FamilyMember>()

        repeat(siblingCount) {
            val siblingGender = Gender.values().random()
            val siblingFirstName = if (siblingGender == Gender.MALE) {
                maleFirstNames.random()
            } else {
                femaleFirstNames.random()
            }
            val siblingAge = Random.nextInt(0, playerAge + 10)
            val siblingRelationship = Random.nextInt(40, 100)

            siblings.add(
                FamilyMember(
                    firstName = siblingFirstName,
                    lastName = playerLastName,
                    age = siblingAge,
                    gender = siblingGender,
                    relationship = siblingRelationship
                )
            )
        }

        return siblings
    }
}