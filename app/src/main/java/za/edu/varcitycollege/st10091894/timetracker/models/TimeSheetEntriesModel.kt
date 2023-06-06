package za.edu.varcitycollege.st10091894.timetracker.models

import android.net.Uri
import java.time.LocalDate
import java.time.LocalTime

data class TimeSheetEntriesModel(val taskName: String, val taskCreationDate: LocalDate, val taskStartTime: LocalTime,
                                 val taskEndTime: LocalTime, val taskClient: String, val taskDescription : String?,
                                 val imageId: Uri?)
