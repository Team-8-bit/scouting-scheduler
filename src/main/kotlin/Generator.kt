import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Generator(val sheetID: String, val eventID: String) {

    suspend fun generate() {

        val tbaKey = this.javaClass.getResource("X-TBA-Auth-Key")?.readText()
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }

        val response = client.get("https://www.thebluealliance.com/api/v3") {
            url {
                appendPathSegments("event", eventID)
            }
            header("X-TBA-Auth-Key", tbaKey)
        }

        println(response.body<EventData>())
    }
}