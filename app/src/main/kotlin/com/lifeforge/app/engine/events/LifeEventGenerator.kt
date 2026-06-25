package com.lifeforge.app.engine.events

import com.lifeforge.app.domain.model.LifeEvent
import com.lifeforge.app.domain.model.LifeEventType
import kotlin.random.Random

object LifeEventGenerator {

    private val birthEvents = listOf(
        LifeEvent(
            title = "Birth",
            description = "You were born into the world.",
            type = LifeEventType.BIRTHDAY
        )
    )

    private val toddlerEvents = listOf(
        LifeEvent(
            title = "First Steps",
            description = "You learned to walk.",
            type = LifeEventType.GENERAL
        ),
        LifeEvent(
            title = "First Words",
            description = "You spoke your first words.",
            type = LifeEventType.GENERAL
        ),
        LifeEvent(
            title = "Kindergarten",
            description = "You started kindergarten and made new friends.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Childhood Wonder",
            description = "You discovered the joy of playing outdoors.",
            type = LifeEventType.SOCIAL
        )
    )

    private val childEvents = listOf(
        LifeEvent(
            title = "School Days Begin",
            description = "You started elementary school.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "New Friend",
            description = "You made a new friend who would become close to you.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "Bicycle Mastery",
            description = "You learned to ride a bicycle.",
            type = LifeEventType.GENERAL
        ),
        LifeEvent(
            title = "Good Grade",
            description = "You got an excellent grade on an important test.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Sports Discovery",
            description = "You discovered a sport you love.",
            type = LifeEventType.SOCIAL
        )
    )

    private val teenEvents = listOf(
        LifeEvent(
            title = "High School",
            description = "You entered high school, a new chapter of your life.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Exam Success",
            description = "You passed an important exam with flying colors.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Sports Team",
            description = "You joined a sports club and found your passion.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "First Crush",
            description = "You experienced your first romantic feelings.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "Academic Achievement",
            description = "You received recognition for your academic performance.",
            type = LifeEventType.EDUCATION
        ),
        LifeEvent(
            title = "Weekend Adventure",
            description = "You had an unforgettable adventure with friends.",
            type = LifeEventType.SOCIAL
        )
    )

    private val adultEvents = listOf(
        LifeEvent(
            title = "Adulthood",
            description = "You became a legal adult.",
            type = LifeEventType.BIRTHDAY
        ),
        LifeEvent(
            title = "Job Interview",
            description = "You had your first job interview. It went well!",
            type = LifeEventType.FINANCE
        ),
        LifeEvent(
            title = "Independence",
            description = "You moved into your own apartment.",
            type = LifeEventType.GENERAL
        ),
        LifeEvent(
            title = "Career Start",
            description = "You got your first real job and started earning money.",
            type = LifeEventType.FINANCE
        ),
        LifeEvent(
            title = "Promotion",
            description = "You received a promotion at work.",
            type = LifeEventType.FINANCE
        ),
        LifeEvent(
            title = "New Friendship",
            description = "You made a meaningful new friend.",
            type = LifeEventType.SOCIAL
        ),
        LifeEvent(
            title = "Health Checkup",
            description = "Your annual health checkup went great.",
            type = LifeEventType.HEALTH
        ),
        LifeEvent(
            title = "Vacation",
            description = "You took a well-deserved vacation and relaxed.",
            type = LifeEventType.GENERAL
        )
    )

    /**
     * Generates 1-3 random life events appropriate for the given age.
     */
    fun generateEvents(age: Int): List<LifeEvent> {
        val eventPool = when {
            age == 0 -> birthEvents
            age in 1..5 -> toddlerEvents
            age in 6..12 -> childEvents
            age in 13..17 -> teenEvents
            else -> adultEvents
        }

        val eventCount = Random.nextInt(1, 4)  // 1-3 events
        return eventPool.shuffled().take(eventCount)
    }
}
