import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object TBA {
    private val tbaKey = this.javaClass.getResource("X-TBA-Auth-Key")?.readText()
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getEventMatches(id: String) = get<List<Match>>("event", id, "matches", "simple")
    suspend fun getEventTeams(id: String) = get<List<String>>("event", id, "teams", "keys")

    private suspend inline fun <reified T> get(vararg pathSegments: String): T {
        val response = client.get("https://www.thebluealliance.com/api/v3") {
            url {
                appendPathSegments(pathSegments.toList())
            }
            header("X-TBA-Auth-Key", tbaKey)
        }
        return response.body<T>()
    }
}

@Serializable
data class Match(
    val key: String,
    val comp_level: String,
    val match_number: Int,
    val alliances: Alliances,
)

@Serializable
data class Alliances(
    val red: Alliance,
    val blue: Alliance,
)

@Serializable
data class Alliance(
    val team_keys: List<String>,
)