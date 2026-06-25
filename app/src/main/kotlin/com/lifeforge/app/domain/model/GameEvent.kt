package com.lifeforge.app.domain.model

data class GameEvent(
    val id: Long = 0L,
    val characterId: Long,
    val title: String,
    val description: String,
    val age: Int,
    val statChanges: Map<String, Int>,
    val moneyChange: Long,
    val timestamp: Long = System.currentTimeMillis()
)

object RandomEvents {
    private val healthEvents = listOf(
        "Caught a cold" to mapOf("health" to -5),
        "Started an exercise routine" to mapOf("health" to 5, "fitness" to 3),
        "Had a great checkup" to mapOf("health" to 10),
        "Got injured" to mapOf("health" to -15),
    )

    private val moneyEvents = listOf(
        "Found money on the street" to 500L,
        "Got a bonus at work" to 5000L,
        "Had to pay an unexpected bill" to -2000L,
        "Won a contest" to 10000L,
    )

    private val intelligenceEvents = listOf(
        "Read a book" to mapOf("intelligence" to 5),
        "Learned something new" to mapOf("intelligence" to 3),
    )

    fun getRandomEvent(): Pair<String, Map<String, Int>>? {
        val allEvents = healthEvents + intelligenceEvents
        return if (Math.random() > 0.3) allEvents.randomOrNull() else null
    }

    fun getRandomMoneyEvent(): Long {
        return if (Math.random() > 0.5) moneyEvents.randomOrNull()?.second ?: 0L else 0L
    }
}
