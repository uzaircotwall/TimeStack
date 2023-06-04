package za.edu.varcitycollege.st10091894.timetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import za.edu.varcitycollege.st10091894.timetracker.databinding.ClientViewholderLayoutBinding
import za.edu.varcitycollege.st10091894.timetracker.models.ClientModel

class ClientAdapter(val items: List<ClientModel>) :
    RecyclerView.Adapter<ClientAdapter.ViewHolder>() {

    inner class ViewHolder(val itemBinding: ClientViewholderLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(clientModel: ClientModel){
            itemBinding.tvClientName.text = clientModel.clientName
            itemBinding.tvClientAcquisitionDate.text = "${clientModel.clientAcquisitionDate.dayOfMonth} " +
                    "${clientModel.clientAcquisitionDate.month} ${clientModel.clientAcquisitionDate.year}"
            itemBinding.tvClientBillableHours.text = clientModel.clientBillableHours.toString()
            itemBinding.tvClientEmail.text = clientModel.clientEmail
            itemBinding.tvClientNotes.text = clientModel.clientNotes
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientAdapter.ViewHolder {
        return ViewHolder(ClientViewholderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}