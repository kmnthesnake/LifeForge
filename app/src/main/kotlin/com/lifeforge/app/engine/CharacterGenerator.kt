package com.lifeforge.app.engine

import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.EducationStage
import com.lifeforge.app.domain.model.Gender
import kotlin.random.Random

object CharacterGenerator {

    private val maleFirstNames = listOf(
        "James", "Robert", "Michael", "William", "David",
        "Richard", "Joseph", "Thomas", "Charles", "Christopher",
        "Daniel", "Matthew", "Anthony", "Mark", "Donald",
        "Steven", "Paul", "Andrew", "Joshua", "Kenneth",
        "Kevin", "Brian", "George", "Edward", "Ronald",
        "Alexander", "Eric", "Jason", "Jonathan", "Stephen"
    )

    private val femaleFirstNames = listOf(
        "Mary", "Patricia", "Jennifer", "Linda", "Barbara",
        "Elizabeth", "Susan", "Jessica", "Sarah", "Karen",
        "Nancy", "Betty", "Margaret", "Sandra", "Ashley",
        "Kimberly", "Emily", "Donna", "Michelle", "Dorothy",
        "Carol", "Amanda", "Melissa", "Deborah", "Stephanie",
        "Rebecca", "Sharon", "Laura", "Cynthia", "Kathleen"
    )

    private val lastNames = listOf(
        "Smith", "Johnson", "Williams", "Brown", "Jones",
        "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
        "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
        "Thomas", "Taylor", "Moore", "Jackson", "Martin",
        "Lee", "Perez", "Thompson", "White", "Harris",
        "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson"
    )

    private val countries = listOf(
        "United States", "Canada", "United Kingdom", "Australia", "Germany",
        "France", "Italy", "Spain", "Japan", "South Korea",
        "Brazil", "Mexico", "India", "China", "Russia",
        "Netherlands", "Sweden", "Norway", "Switzerland", "Denmark"
    )

    private val cities = mapOf(
        "United States" to listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix"),
        "Canada" to listOf("Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa"),
        "United Kingdom" to listOf("London", "Manchester", "Birmingham", "Leeds", "Glasgow"),
        "Australia" to listOf("Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide"),
        "Germany" to listOf("Berlin", "Munich", "Frankfurt", "Cologne", "Hamburg"),
        "France" to listOf("Paris", "Lyon", "Marseille", "Toulouse", "Nice"),
        "Italy" to listOf("Rome", "Milan", "Naples", "Florence", "Venice"),
        "Spain" to listOf("Madrid", "Barcelona", "Valencia", "Seville", "Bilbao"),
        "Japan" to listOf("Tokyo", "Osaka", "Kyoto", "Yokohama", "Nagoya"),
        "Brazil" to listOf("São Paulo", "Rio de Janeiro", "Brasília", "Salvador", "Fortaleza")
    )

    /**
     * Generates a random Character with all randomized attributes within specified ranges
     */
    fun generate(): Character {
        val gender = Gender.values().random()
        val firstName = if (gender == Gender.MALE) {
            maleFirstNames.random()
        } else {
            femaleFirstNames.random()
        }
        val lastName = lastNames.random()
        val country = countries.random()
        val city = cities[country]?.random() ?: "Unknown City"
        val bodyType = BodyType.values().random()

        val health = Random.nextInt(60, 101)          // 60-100
        val happiness = Random.nextInt(50, 101)       // 50-100
        val intelligence = Random.nextInt(40, 101)    // 40-100
        val looks = Random.nextInt(40, 101)           // 40-100
        val fitness = Random.nextInt(30, 101)         // 30-100
        val money = Random.nextLong(0, 1001)          // 0-1000

        // Generate family
        val motherName = FamilyGenerator.generateMother(lastName)
        val fatherName = FamilyGenerator.generateFather(lastName)
        val siblings = Random.nextInt(0, 5)            // 0-4 siblings

        return Character(
            firstName = firstName,
            lastName = lastName,
            age = 0,
            gender = gender,
            country = country,
            city = city,
            bodyType = bodyType,
            health = health,
            happiness = happiness,
            intelligence = intelligence,
            looks = looks,
            fitness = fitness,
            money = money,
            alive = true,
            motherName = motherName,
            fatherName = fatherName,
            siblings = siblings,
            relationshipMother = Random.nextInt(60, 100),
            relationshipFather = Random.nextInt(60, 100),
            educationStage = EducationStage.BABY
        )
    }
}