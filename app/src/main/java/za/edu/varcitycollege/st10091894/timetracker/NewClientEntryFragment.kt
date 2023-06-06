package za.edu.varcitycollege.st10091894.timetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.datepicker.MaterialDatePicker
import za.edu.varcitycollege.st10091894.timetracker.Lists.ClientList
import za.edu.varcitycollege.st10091894.timetracker.models.ClientModel
import java.time.LocalDate


class NewClientEntryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_client_entry, container, false)

        //show date date picker dialog when user clicks dateAcquisition editText view and display
        // whatever the user selected as a date
        var _clientAcquisitionDate : Long = LocalDate.now().toEpochDay()
        val clientAcquisitionDate = view.findViewById<EditText>(R.id.etvClientAqcuisitionDate)

        clientAcquisitionDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                datePicker.show(parentFragmentManager, "datePicker")
                datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.

                //convert unix epoch value from milliseconds to days
                _clientAcquisitionDate = it / (24 * 60 * 60 * 1000)
                clientAcquisitionDate.setText(
                    "${LocalDate.ofEpochDay(_clientAcquisitionDate).dayOfMonth} " +
                    "${LocalDate.ofEpochDay(_clientAcquisitionDate).month} " +
                    "${LocalDate.ofEpochDay(_clientAcquisitionDate).year}"
                )

            }
        }


        //extract data from the views
        val clientName = view.findViewById<EditText>(R.id.etvClientName).text
        val clientEmail = view.findViewById<EditText>(R.id.etvClientEmail).text
        val clientNotes = view.findViewById<EditText>(R.id.etvClientNotes).text
        val btnAddClient = view.findViewById<Button>(R.id.btnAddClient)

        btnAddClient.setOnClickListener {

            //create client object
            var client = ClientModel(
                clientName.toString(),
                LocalDate.of(
                    LocalDate.ofEpochDay(_clientAcquisitionDate).year,
                    LocalDate.ofEpochDay(_clientAcquisitionDate).month,
                    LocalDate.ofEpochDay(_clientAcquisitionDate).dayOfMonth, )?: LocalDate.now(),
                0,
                clientEmail.toString(),
                clientNotes.toString()
            )

            //store client object in list
            ClientList.clientList.add(client)

            //navigate back to client list fragment
            val clientFragment = TimeSheetClientFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameLayout, clientFragment)
            transaction.commit()


        }

        return view
    }


}