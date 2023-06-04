package za.edu.varcitycollege.st10091894.timetracker.adapters

import za.edu.varcitycollege.st10091894.timetracker.models.ClientModel
import java.time.LocalDate

object ClientList {
    val clientList = mutableListOf<ClientModel>(
        //sample data
        ClientModel("Adapt IT", LocalDate.of(2023, 5,25), 4,
            "info@adaptit.com", ""),

        ClientModel("GluCode", LocalDate.of(2023, 2,18), 4,
            "info@glucode.com", "")

    )
}