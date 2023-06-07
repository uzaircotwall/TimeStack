package za.edu.varcitycollege.st10091894.timetracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import za.edu.varcitycollege.st10091894.timetracker.adapters.TimeSheetEntriesAdapter
import za.edu.varcitycollege.st10091894.timetracker.Lists.TimeSheetEntriesList
import za.edu.varcitycollege.st10091894.timetracker.models.TimeSheetEntriesModel
import java.time.LocalDate


class TimeSheetEntriesFragment : Fragment() {

    private lateinit var adapter: TimeSheetEntriesAdapter
    private lateinit var recyclerView: RecyclerView
    var updatedTimeSheetsList: MutableList<TimeSheetEntriesModel>? = mutableListOf()
    private var dateFilter : Long = LocalDate.now().toEpochDay().minus(1670288400)
    private var max_work_hours_target = 0
    private var min_work_hours_target = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_time_sheet_entries, container, false)

        //navigate to NewTimeSheetEntryFragment
        val btnNewEntry = view.findViewById<Button>(R.id.btnNewEntry)
        btnNewEntry.setOnClickListener {
            //validating the input

            val newEntryFragment = NewTimeSheetEntryFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameLayout, newEntryFragment)
            transaction.commit()


        }
        val edtDateFilter = view.findViewById<EditText>(R.id.categoryListFilter)

        //initialize list timesheetEntries list
        //updatedTimeSheetsList = ArrayList()
        //dateFilter = 0
        //updateRecyclerView()
        //bind recyclerview
        updatedTimeSheetsList = TimeSheetEntriesList.entryList
        recyclerView = view.findViewById(R.id.task_item)
        adapter = TimeSheetEntriesAdapter(TimeSheetEntriesList.entryList)
        recyclerView.adapter = adapter


        //update recycler view after user enters a filter date

        edtDateFilter.setOnClickListener{
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            datePicker.show(parentFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.

                //convert unix epoch value from milliseconds to days
                dateFilter = it / (24 * 60 * 60 * 1000)
                edtDateFilter.setText(
                    "${LocalDate.ofEpochDay(dateFilter).dayOfMonth} " +
                          "${LocalDate.ofEpochDay(dateFilter).month} " +
                            "${LocalDate.ofEpochDay(dateFilter).year}"
                )

            }

        }

        edtDateFilter.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                /**var resultNotFound = true
                TimeSheetEntriesList.entryList.forEach {
                    if (dateFilter <= 1 ){} else {

                        if (it.taskCreationDate.isAfter(LocalDate.ofEpochDay(dateFilter))) {
                            updatedTimeSheetsList?.add(it)
                            resultNotFound = false
                        }

                    }
                }
                    if (resultNotFound) {
                        Toast.makeText(
                            requireContext(), "There are no entries after" +
                                    "${LocalDate.ofEpochDay(dateFilter).dayOfMonth} " +
                                    "${LocalDate.ofEpochDay(dateFilter).month} " +
                                    "${LocalDate.ofEpochDay(dateFilter).year}", Toast.LENGTH_LONG

                        ).show()
                    } **/

            }
        })




        //progress bar (gamification feature) section
        //set the maximum work hours target goal progress bar
        val maxHoursProgressBar = view.findViewById<ProgressBar>(R.id.maxHoursPB)
        val maxWorkHoursTarget = view.findViewById<EditText>(R.id.maxWorkHoursTarget)

        maxWorkHoursTarget.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (maxWorkHoursTarget.text.toString() != ""){

                    max_work_hours_target = maxWorkHoursTarget.text.toString().toInt()

                    //set and display progress on progress bar
                    maxHoursProgressBar.setProgress(calcTotalHoursWorkedToday())
                    maxHoursProgressBar.setMax(max_work_hours_target)
                }
            }

        })


        //now we set the minimum work hours target goal progress bar
        val minHoursProgressBar = view.findViewById<ProgressBar>(R.id.minHoursPB)
        val minWorkHoursTarget = view.findViewById<EditText>(R.id.minHoursWorked)

        minWorkHoursTarget.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (minWorkHoursTarget.text.toString() != "") {

                    min_work_hours_target = minWorkHoursTarget.text.toString().toInt()

                    //set and display progress on progress bar
                    minHoursProgressBar.setProgress(calcTotalHoursWorkedToday())
                    minHoursProgressBar.setMax(min_work_hours_target)
                }

            }

        })

        //set event for congratulating user when progress bar hits 100%




        return view
    }

    private fun calcTotalHoursWorkedToday(): Int {
        //return the number of hours the user has worked today
        var totalHoursWorked: Int = 0
        TimeSheetEntriesList.entryList.forEach {
            if (it.taskCreationDate == LocalDate.now()){
                totalHoursWorked += it.taskEndTime.hour.minus(it.taskStartTime.hour)
            }
        }
        return totalHoursWorked
    }



}