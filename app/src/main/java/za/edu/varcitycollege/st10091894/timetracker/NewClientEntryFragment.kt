package za.edu.varcitycollege.st10091894.timetracker

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import za.edu.varcitycollege.st10091894.timetracker.adapters.ClientAdapter
import za.edu.varcitycollege.st10091894.timetracker.adapters.ClientList
import za.edu.varcitycollege.st10091894.timetracker.models.ClientModel
import java.time.LocalDate
import java.time.Month
import java.util.Calendar


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
        var _clientAcquisitionDate : LocalDate = LocalDate.now()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val clientAcquisitionDate = view.findViewById<EditText>(R.id.etvClientAqcuisitionDate)

        /**clientAcquisitionDate.setOnClickListener {
            val dpd = DatePickerDialog(NewClientEntryFragment().requireContext(), DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    _clientAcquisitionDate = LocalDate.of(mYear, mMonth, mDay)
                    clientAcquisitionDate.setText("${_clientAcquisitionDate.dayOfMonth} ${_clientAcquisitionDate.month} ${_clientAcquisitionDate.year}")
                }, year, month, day)

            dpd.show()
        }**/

        //extract data from the views
        val clientName = view.findViewById<EditText>(R.id.etvClientName).text
        val clientEmail = view.findViewById<EditText>(R.id.etvClientEmail).text
        val clientNotes = view.findViewById<EditText>(R.id.etvClientNotes).text
        val btnAddClient = view.findViewById<Button>(R.id.btnAddClient)

        btnAddClient.setOnClickListener {

            //create client object
            var client = ClientModel(clientName.toString(), LocalDate.of(_clientAcquisitionDate.year,
                _clientAcquisitionDate.month, _clientAcquisitionDate.dayOfMonth )?: LocalDate.now(),
                0, clientEmail.toString(), clientNotes.toString())

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