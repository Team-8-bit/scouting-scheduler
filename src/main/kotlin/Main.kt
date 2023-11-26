import kotlinx.serialization.Serializable

suspend fun main(args: Array<String>) {
    val iterator = args.iterator()
    while (iterator.hasNext()) {
        when (iterator.next()) {
            "--test" -> {
                val generator = Generator(
                    sheetID = "1bi-MqGwfqEBgKGzkezM0M2CW7xJw066YwshSpxw6uM4",
                    eventID = "2023azgl"
                )

                generator.generate()
                return
            }
        }
    }
}

@Serializable
data class EventData(
    val name: String?,
)