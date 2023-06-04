package za.edu.varcitycollege.st10091894.timetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import za.edu.varcitycollege.st10091894.timetracker.adapters.ClientAdapter
import za.edu.varcitycollege.st10091894.timetracker.adapters.ClientList


class TimeSheetClientFragment : Fragment() {

    private lateinit var adapter: ClientAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_time_sheet_client, container, false)

        recyclerView = view.findViewById(R.id.client_item)
        adapter = ClientAdapter(ClientList.clientList)
        recyclerView.adapter = adapter

        return view
    }


}