import java.time.Instant

suspend fun main() {
    val startTime = Instant.now().toEpochMilli()
    val generator = Generator(
        sheetID = "1bi-MqGwfqEBgKGzkezM0M2CW7xJw066YwshSpxw6uM4",
        eventID = "2024azva"
    )

    generator.generate()
    val timeTaken = Instant.now().toEpochMilli() - startTime
    println("Done in ${timeTaken}ms!")
}