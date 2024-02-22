package com.samrudhasolutions.bolo.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samrudhasolutions.bolo.R

class FolderAdapter(private val folderList: List<String>, private val onFolderClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_item, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folderName = folderList[position]
        holder.bind(folderName)
        holder.itemView.setOnClickListener { onFolderClickListener.invoke(position) }
    }

    override fun getItemCount(): Int {
        return folderList.size
    }

    class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val folderNameTextView: TextView = itemView.findViewById(R.id.folderNameTextView)

        fun bind(folderName: String) {
            folderNameTextView.text = folderName
        }
    }
}
