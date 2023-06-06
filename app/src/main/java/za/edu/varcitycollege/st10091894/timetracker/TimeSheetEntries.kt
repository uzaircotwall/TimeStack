package za.edu.varcitycollege.st10091894.timetracker
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import za.edu.varcitycollege.st10091894.timetracker.databinding.FragmentTimeSheetEntriesBinding


class TimeSheetEntries: Fragment(R.layout.fragment_time_sheet_entries) {
    private var _binding : FragmentTimeSheetEntriesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTimeSheetEntriesBinding.bind(view)

        binding.apply {
            categoryListFilter.setOnClickListener {
                //new instance of DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                val supportFragmnetManager = requireActivity().supportFragmentManager

                //implement setFragmentResultListener
                supportFragmnetManager.setFragmentResultListener(
                    "REQUEST_KEY", viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        categoryListFilter.text = date
                    }
                }
            }
        }
    }

}