package za.edu.varcitycollege.st10091894.timetracker.Lists

import za.edu.varcitycollege.st10091894.timetracker.R
import za.edu.varcitycollege.st10091894.timetracker.models.TimeSheetEntriesModel
import java.time.LocalDate
import java.time.LocalTime

object TimeSheetEntriesList {
    val entryList = mutableListOf<TimeSheetEntriesModel>(
        //sample data
        TimeSheetEntriesModel("Create interface", LocalDate.of(2023, 5,30), LocalTime.of(12, 4),
            LocalTime.of(16, 55), "AdaptIt", "Design new user login page.", null),

        TimeSheetEntriesModel("Fix bug in database query", LocalDate.of(2023, 4, 2),
            LocalTime.of(9, 0), LocalTime.of(16, 30), "GluCode",
            "Investigate and fix the issue with the database query.", null)

    )
}