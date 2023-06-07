package za.edu.varcitycollege.st10091894.timetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import za.edu.varcitycollege.st10091894.timetracker.Lists.TimeSheetEntriesList
import za.edu.varcitycollege.st10091894.timetracker.databinding.TaskViewholderLayoutBinding
import za.edu.varcitycollege.st10091894.timetracker.models.TimeSheetEntriesModel

class TimeSheetEntriesAdapter(var items: MutableList<TimeSheetEntriesModel>) :
    RecyclerView.Adapter<TimeSheetEntriesAdapter.ViewHolder>(){

    /**fun update(updatedList: MutableList<TimeSheetEntriesModel>){
        items = mutableListOf()
        if (updatedList.size == 0) items.addAll(TimeSheetEntriesList.entryList) else items.addAll(updatedList)

        notifyDataSetChanged()
    } **/
    //make sure you have enabled viewBinding by setting viewBinding to true in the build.gradle file
    inner class ViewHolder(val itemBinding: TaskViewholderLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(taskModel: TimeSheetEntriesModel){
            itemBinding.tvTaskName.text = taskModel.taskName
            itemBinding.tvTaskCreationDate.text = "${taskModel.taskCreationDate.dayOfMonth} " +
                    "${taskModel.taskCreationDate.month} ${taskModel.taskCreationDate.year}"
            itemBinding.tvTaskStartTime.text = "${taskModel.taskStartTime.hour}:${taskModel.taskStartTime.minute}"
            itemBinding.tvTaskEndTime.text = "${taskModel.taskEndTime.hour}:${taskModel.taskEndTime.minute}"
            itemBinding.tvTaskClient.text = taskModel.taskClient
            itemBinding.imgTaskImage.setImageURI(taskModel.imageId?: null)
            itemBinding.tvTaskDescription.text = taskModel.taskDescription

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskViewholderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}