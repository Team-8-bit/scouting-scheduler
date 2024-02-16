import java.time.Instant

suspend fun main() {
    val startTime = Instant.now().toEpochMilli()
    val generator = Generator(eventID = "2024azva")

    generator.generate()
    val timeTaken = Instant.now().toEpochMilli() - startTime
    println("Done in ${timeTaken}ms!")
}