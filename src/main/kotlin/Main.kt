import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.Instant

val json = Json {
    prettyPrint = true
}

suspend fun main() {
    val startTime = Instant.now().toEpochMilli()
    val generator = Generator(eventID = "2023azgl")

    val output = generator.generate()
    val outputFilePath = "./"

    val outputJson = json.encodeToString(output)

    val outputFile = File(outputFilePath, "schedule.json")
    outputFile.createNewFile()

    outputFile.writeText(outputJson)

    val timeTaken = Instant.now().toEpochMilli() - startTime
    println("Done in ${timeTaken}ms!")
}