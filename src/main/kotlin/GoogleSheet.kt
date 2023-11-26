import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials

class GoogleSheet(private val id: String) {
    private val credentials: GoogleCredentials = GoogleCredentials.fromStream(this.javaClass.getResourceAsStream("credentials.json")).createScoped("https://www.googleapis.com/auth/drive")
    private val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()

    operator fun get(range: String): List<List<String>> {
        credentials.refreshIfExpired()
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val service = Sheets.Builder(httpTransport, jsonFactory, HttpCredentialsAdapter(credentials)).setApplicationName("Inventory App").build()
        val response = service.spreadsheets().values().get(id, range).execute()
        return response.getValues().map { it.map(Any::toString) }
    }

    operator fun set(range: String, values: List<List<String>>) {
        credentials.refreshIfExpired()
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val service = Sheets.Builder(httpTransport, jsonFactory, HttpCredentialsAdapter(credentials)).setApplicationName("Inventory App").build()
        val newRange = ValueRange().setValues(values)
        service.spreadsheets().values().update(id, range, newRange).setValueInputOption("USER_ENTERED").execute()
    }
}