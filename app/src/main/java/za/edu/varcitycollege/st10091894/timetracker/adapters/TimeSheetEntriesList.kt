package za.edu.varcitycollege.st10091894.timetracker.adapters

import za.edu.varcitycollege.st10091894.timetracker.R
import za.edu.varcitycollege.st10091894.timetracker.models.TimeSheetEntriesModel
import java.time.LocalDate
import java.time.LocalTime

object TimeSheetEntriesList {
    val entryList = mutableListOf<TimeSheetEntriesModel>(
        //sample data
        TimeSheetEntriesModel("Create interface", LocalDate.of(2023, 5,30), LocalTime.of(12, 4),
            LocalTime.of(16, 55), "AdaptIt", "Design new user login page.", null),

        TimeSheetEntriesModel("Create interface", LocalDate.now(), LocalTime.of(12, 4),
            LocalTime.of(13, 4), "AdaptIt", "Design new user login page.", null)

    )
}