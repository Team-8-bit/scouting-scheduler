class Generator(val eventID: String) {

    suspend fun generate(): ScheduleInformation {
        println("Updating match data...")
        val matches = TBA.getEventMatches(eventID).filter { it.comp_level == "qm" }.sortedBy { it.match_number }
        val processedMatches = matches.map {
            ProcessedMatchData(
                it.match_number.toString(),
                it.alliances.red.team_keys[0].removePrefix("frc"),
                it.alliances.red.team_keys[1].removePrefix("frc"),
                it.alliances.red.team_keys[2].removePrefix("frc"),
                it.alliances.blue.team_keys[0].removePrefix("frc"),
                it.alliances.blue.team_keys[1].removePrefix("frc"),
                it.alliances.blue.team_keys[2].removePrefix("frc"),
            )
        }
        val schedules = mutableMapOf<Int, MutableMap<String, String>>()

        for (i in 1..12) schedules[i] = mutableMapOf()

        processedMatches.forEach {
            val match = it.matchNumber

            schedules[1]!![match] = it.redOne
            schedules[2]!![match] = it.redTwo
            schedules[3]!![match] = it.redThree
            schedules[4]!![match] = it.blueOne
            schedules[5]!![match] = it.blueTwo
            schedules[6]!![match] = it.blueThree
            schedules[7]!![match] = it.redOne
            schedules[8]!![match] = it.redTwo
            schedules[9]!![match] = it.redThree
            schedules[10]!![match] = it.blueOne
            schedules[11]!![match] = it.blueTwo
            schedules[12]!![match] = it.blueThree
        }

        return ScheduleInformation(
            eventID = eventID,
            superscoutIDs = listOf(7, 8, 9, 10, 11, 12),
            scoutingSchedule = schedules.mapValues { ScoutingSchedule(it.value) }
        )
    }

    data class ProcessedMatchData(
        val matchNumber: String,
        val redOne: String,
        val redTwo: String,
        val redThree: String,
        val blueOne: String,
        val blueTwo: String,
        val blueThree: String,
    )
}


