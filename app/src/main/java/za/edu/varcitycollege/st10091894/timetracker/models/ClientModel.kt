package za.edu.varcitycollege.st10091894.timetracker.models

import java.time.LocalDate

data class ClientModel(val clientName: String, val clientAcquisitionDate: LocalDate, val clientBillableHours: String,
                       val clientEmail: String, val clientNotes: String)
