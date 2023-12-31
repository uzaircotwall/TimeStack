package za.edu.varcitycollege.st10091894.timetracker

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.Manifest
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import za.edu.varcitycollege.st10091894.timetracker.Lists.ClientList
import za.edu.varcitycollege.st10091894.timetracker.Lists.TimeSheetEntriesList
import za.edu.varcitycollege.st10091894.timetracker.Lists.UsersList
import za.edu.varcitycollege.st10091894.timetracker.models.TimeSheetEntriesModel
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.LocalTime


class NewTimeSheetEntryFragment : Fragment() {
    var _taskDate : Long = LocalDate.now().toEpochDay()

    var _taskStartTimeHour : Int = LocalTime.now().hour
    var _taskStartTimeMin : Int = LocalTime.now().minute

    var _taskEndTimeHour : Int = LocalTime.now().hour
    var _taskEndTimeMin : Int = LocalTime.now().minute

    var _taskClient : String? = null

    private var photoUri: Uri? = null
    private lateinit var addPhoto : ImageView
    companion object{
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_time_sheet_entry, container, false)

        //Camera functionality
        addPhoto = view.findViewById<ImageView>(R.id.img_task_image)

        addPhoto.setOnClickListener{
        checkCameraPermission()
        }

        //Now we extract the data from all the fields
        val taskName = view.findViewById<EditText>(R.id.edtPersonEmail)
        val taskClientSpinner = view.findViewById<Spinner>(R.id.clientName)
        val taskStartTime = view.findViewById<EditText>(R.id.edtStartTime)
        val taskEndTime = view.findViewById<EditText>(R.id.edtEndTime)
        val taskDescription = view.findViewById<EditText>(R.id.etvTaskDescription)
        val taskDate = view.findViewById<EditText>(R.id.edtDate)

        //spinner functionality allowing the user to choose a client

        var clientList = mutableListOf<String>()
        ClientList.clientList.forEach {

            clientList.add(it.clientName)
        }
        clientList.add("Create New")
        val arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_text_layout, clientList)
        taskClientSpinner.adapter = arrayAdapter
        taskClientSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                _taskClient = clientList[p2]
                if (_taskClient.equals("Create New")){
                    //navigate back to timeSheetEntries fragment list fragment
                    val newClientFragment = NewClientEntryFragment()
                    val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaction.replace(R.id.frameLayout, newClientFragment)
                    transaction.commit()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        //DatePicker popup for task creation date field
        taskDate.setOnClickListener{
            val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(parentFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.

                //convert unix epoch value from milliseconds to days
                _taskDate = it / (24 * 60 * 60 * 1000)
                taskDate.setText(
                    "${LocalDate.ofEpochDay(_taskDate).dayOfMonth} " +
                            "${LocalDate.ofEpochDay(_taskDate).month} " +
                            "${LocalDate.ofEpochDay(_taskDate).year}"
                )
            }
        }


        //timePicker popup for task start time field
         taskStartTime.setOnClickListener {
             val isSystem24Hour = is24HourFormat(requireContext())
             val clockFormat = if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

             val picker = MaterialTimePicker.Builder()
                 .setTimeFormat(clockFormat)
                 .setHour(12)
                 .setMinute(0)
                 .setTitleText("Select Start Time")
                 .build()
             picker.show(childFragmentManager, "TAG")

             picker.addOnPositiveButtonClickListener{
                 _taskStartTimeHour = picker.hour
                 _taskStartTimeMin = picker.minute

                 taskStartTime.setText("${_taskStartTimeHour}:${_taskStartTimeMin}")
             }
        }


        taskEndTime.setOnClickListener {
            val isSystem24Hour = is24HourFormat(requireContext())
            val clockFormat = if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Start Time")
                .build()
            picker.show(childFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener{
                _taskEndTimeHour = picker.hour
                _taskEndTimeMin = picker.minute

                taskEndTime.setText("${_taskEndTimeHour}:${_taskEndTimeMin}")
            }
        }



        view.findViewById<Button>(R.id.btnNewEntry).setOnClickListener {
            if (isInputValid(taskName = taskName, creationDate = taskDate, startTime = taskStartTime, endTime = taskEndTime)){
                //create timeSheetEntries object
                val timeSheetEntryModel = TimeSheetEntriesModel(taskName.text.toString(),
                    LocalDate.of(
                        LocalDate.ofEpochDay(_taskDate).year,
                        LocalDate.ofEpochDay(_taskDate).month,
                        LocalDate.ofEpochDay(_taskDate).dayOfMonth, )?: LocalDate.now(),
                    LocalTime.of(_taskStartTimeHour, _taskStartTimeMin),
                    LocalTime.of(_taskEndTimeHour, _taskEndTimeMin),
                    _taskClient?: "null",
                    taskDescription.text.toString(),
                    photoUri)
                //save the entry
                TimeSheetEntriesList.entryList.add(timeSheetEntryModel)

                //navigate back to timeSheetEntries fragment list fragment
                val timeSheetEntries = TimeSheetEntriesFragment()
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.frameLayout, timeSheetEntries)
                transaction.commit()
            }






        }

        return view
    }




    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            launchCamera()
        }
    }

    private fun launchCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } else {
            Toast.makeText(requireContext(), "Camera not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val imageUri = saveImageToInternalStorage(imageBitmap)
            photoUri = imageUri
            addPhoto.setImageURI(imageUri)
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): Uri? {
        val wrapper = ContextWrapper(requireContext())
        val file = wrapper.getDir("images", Context.MODE_PRIVATE)
        val fileName = "captured_image.jpg"

        val imageFile = File(file, fileName)
        val outputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        photoUri = Uri.fromFile(imageFile)

        return photoUri
    }
    private fun isInputValid(taskName: EditText, creationDate: EditText, startTime: EditText, endTime: EditText): Boolean {
        // Implement your own validation logic here
        var isValid = true
        if (taskName.text.toString().isEmpty()) {
            isValid = false
            taskName.error = "please enter a task name"
        }
        if (creationDate.text.toString().isEmpty()) {
            isValid = false
            creationDate.error = "please enter a date"
        }
        if (startTime.text.toString().isEmpty()) {
            isValid = false
            startTime.error = "please enter a start time"
        }
        if (endTime.text.toString().isEmpty()) {
            isValid = false
            endTime.error = "please enter a start time"
        }

        return isValid
    }

}

