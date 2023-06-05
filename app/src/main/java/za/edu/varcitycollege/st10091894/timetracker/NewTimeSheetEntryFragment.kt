package za.edu.varcitycollege.st10091894.timetracker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class NewTimeSheetEntryFragment : Fragment() {

    private val REQUEST_CODE_PHOTO = 1
    private var photoUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_time_sheet_entry, container, false)

        // Find the addPhotoButton using its id
        val addPhotoButton = view.findViewById<Button>(R.id.addPhotoButton)

        // Sets a click listener for the addPhotoButton
        addPhotoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_PHOTO)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK) {
            photoUri = data?.data
            // do something with the photoUri here
        }
    }
}

