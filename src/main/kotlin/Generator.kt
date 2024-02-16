class Generator(val sheetID: String, val eventID: String) {
    private val googleSheet = GoogleSheet(sheetID)

    suspend fun generate() {
        println("Updating match data...")
        val matchData = TBA.getEventMatches(eventID).filter { it.comp_level == "qm" }.sortedBy { it.match_number }
        val matchScoutingData = getMatchScoutingData(matchData)
        googleSheet["MatchScouting!A2:H1000"] = matchScoutingData
    }

    private fun getMatchScoutingData(matches: List<Match>): MutableSheetData {
        val data: MutableSheetData = mutableListOf()
        matches.forEach {
            val row = mutableListOf<String>()
            row.add(it.match_number.toString())
            row.add(it.alliances.red.team_keys[0].removePrefix("frc"))
            row.add(it.alliances.red.team_keys[1].removePrefix("frc"))
            row.add(it.alliances.red.team_keys[2].removePrefix("frc"))
            row.add(it.alliances.blue.team_keys[0].removePrefix("frc"))
            row.add(it.alliances.blue.team_keys[1].removePrefix("frc"))
            row.add(it.alliances.blue.team_keys[2].removePrefix("frc"))
            row.add(it.key)
            data.add(row)
        }
        return data
    }
}


