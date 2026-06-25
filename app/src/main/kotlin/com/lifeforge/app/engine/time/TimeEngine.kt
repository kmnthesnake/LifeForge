package com.lifeforge.app.engine.time

import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.EducationStage
import com.lifeforge.app.domain.model.LifeEvent
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object TimeEngine {

    /**
     * Advances character's age by one year and generates life events.
     * Updates character statistics according to age-based rules.
     * Updates education stage based on age and intelligence.
     */
    fun ageUp(character: Character): Pair<Character, List<LifeEvent>> {
        val newAge = character.age + 1

        // Calculate stat changes
        val healthChange = Random.nextInt(-3, 3)  // -3 to +2
        val happinessChange = Random.nextInt(-5, 6)  // -5 to +5
        val fitnessChange = Random.nextInt(-3, 4)  // -3 to +3
        val looksChange = Random.nextInt(-2, 3)  // -2 to +2

        // Money increase only after age 18
        val moneyIncrease = if (newAge < 18) 0L else Random.nextLong(100, 501)

        // Intelligence increases based on age
        val intelligenceIncrease = when {
            newAge <= 5 -> 5
            newAge <= 12 -> 3
            newAge <= 18 -> 2
            else -> 0
        }

        // Apply changes with clamping
        val newHealth = clampStat(character.health + healthChange)
        val newHappiness = clampStat(character.happiness + happinessChange)
        val newFitness = clampStat(character.fitness + fitnessChange)
        val newLooks = clampStat(character.looks + looksChange)
        val newIntelligence = clampStat(character.intelligence + intelligenceIncrease)
        val newMoney = character.money + moneyIncrease

        // Update relationships with parents
        val motherRelationshipChange = Random.nextInt(-5, 6)  // -5 to +5
        val fatherRelationshipChange = Random.nextInt(-5, 6)  // -5 to +5
        val newMotherRelationship = clampStat(character.relationshipMother + motherRelationshipChange)
        val newFatherRelationship = clampStat(character.relationshipFather + fatherRelationshipChange)

        // Update education stage
        val newEducationStage = getEducationStage(newAge, newIntelligence, character.educationStage)

        // Create updated character
        val updatedCharacter = character.copy(
            age = newAge,
            health = newHealth,
            happiness = newHappiness,
            fitness = newFitness,
            looks = newLooks,
            intelligence = newIntelligence,
            money = newMoney,
            relationshipMother = newMotherRelationship,
            relationshipFather = newFatherRelationship,
            educationStage = newEducationStage,
            updatedAt = System.currentTimeMillis()
        )

        // Generate events for this year
        val events = LifeEventGenerator.generateEvents(newAge, newEducationStage)

        return Pair(updatedCharacter, events)
    }

    private fun getEducationStage(age: Int, intelligence: Int, currentStage: EducationStage): EducationStage {
        return when {
            age < 3 -> EducationStage.BABY
            age < 5 -> EducationStage.TODDLER
            age < 6 -> EducationStage.KINDERGARTEN
            age < 12 -> EducationStage.PRIMARY_SCHOOL
            age < 15 -> EducationStage.MIDDLE_SCHOOL
            age < 18 -> EducationStage.HIGH_SCHOOL
            age == 18 && intelligence >= 65 -> EducationStage.UNIVERSITY
            age > 18 && currentStage == EducationStage.UNIVERSITY -> EducationStage.GRADUATED
            age > 18 -> EducationStage.GRADUATED
            else -> currentStage
        }
    }

    private fun clampStat(value: Int): Int = min(100, max(0, value))
}

object LifeEventGenerator {

    private val babyToddlerEvents = listOf(
        LifeEvent(
            title = "Birth",
            description = "You were born into the world.",
            type = LifeEventType.BIRTHDAY
        ),
        LifeEvent(
            title = "First Words",
            description = "You spoke your first words.",
            type = LifeEventType.GENERAL
        ),
        LifeEvent(
            title = "First Steps",
            description = "You learned to walk.",
            type = LifeEventType.GENERAL
        )
    )

    private val kindergartenEvents = listOf(
        LifeEvent(
            title = "First Day",
            description = "You started kindergarten.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "New Friend",
            description = "You made a new friend.",
            type = LifeEventType.SOCIAL
        )
    )

    private val primarySchoolEvents = listOf(
        LifeEvent(
            title = "First Day of School",
            description = "You started primary school.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Made a New Friend",
            description = "You made a new friend who would become close to you.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "School Competition",
            description = "You won a school competition.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Failed an Exam",
            description = "You failed an important exam.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Good Grade",
            description = "You got an excellent grade.",
            type = LifeEventType.EDUCATION
        )
    )

    private val middleSchoolEvents = listOf(
        LifeEvent(
            title = "Middle School",
            description = "You started middle school.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "School Competition",
            description = "You won a school competition.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Failed Exam",
            description = "You failed an important exam.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "New Friend",
            description = "You made a new friend.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "Scholarship Offer",
            description = "You received a scholarship offer.",
            type = LifeEventType.EDUCATION
        )
    )

    private val highSchoolEvents = listOf(
        LifeEvent(
            title = "High School",
            description = "You entered high school.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Passed Exam",
            description = "You passed an important exam.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Failed Exam",
            description = "You failed an important exam.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Joined Club",
            description = "You joined a school club.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "First Crush",
            description = "You experienced your first romantic feelings.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "Party Night",
            description = "You went to an unforgettable party.",
            type = LifeEventType.SOCIAL
        )
    )

    private val universityEvents = listOf(
        LifeEvent(
            title = "Accepted to University",
            description = "You were accepted to university!",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "University Life",
            description = "You started your university education.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Research Project",
            description = "You completed an important research project.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Failed Class",
            description = "You failed a class at university.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Campus Party",
            description = "You attended a memorable campus party.",
            type = LifeEventType.SOCIAL
        )
    )

    private val graduatedEvents = listOf(
        LifeEvent(
            title = "Graduated",
            description = "You graduated from your education.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Job Interview",
            description = "You had a job interview.",
            type = LifeEventType.FINANCE
        ),
        LifeEvent(
            title = "Career Start",
            description = "You got your first job.",
            type = LifeEventType.FINANCE
        ),
        LifeEvent(
            title = "Promotion",
            description = "You received a promotion at work.",
            type = LifeEventType.FINANCE
        )
    )

    /**
     * Generates 1-3 random life events appropriate for the given age and education stage.
     */
    fun generateEvents(age: Int, educationStage: EducationStage): List<LifeEvent> {
        val eventPool = when {
            age == 0 -> babyToddlerEvents
            age < 5 -> babyToddlerEvents
            age < 6 -> kindergartenEvents
            age < 12 -> primarySchoolEvents
            age < 15 -> middleSchoolEvents
            age < 18 -> highSchoolEvents
            educationStage == EducationStage.UNIVERSITY -> universityEvents
            else -> graduatedEvents
        }

        val eventCount = Random.nextInt(1, 4)  // 1-3 events
        return eventPool.shuffled().take(eventCount)
    }
}

enum class LifeEventType {
    BIRTHDAY,
    EDUCATION,
    HEALTH,
    SOCIAL,
    FINANCE,
    GENERAL
}

data class LifeEvent(
    val title: String,
    val description: String,
    val type: LifeEventType
)