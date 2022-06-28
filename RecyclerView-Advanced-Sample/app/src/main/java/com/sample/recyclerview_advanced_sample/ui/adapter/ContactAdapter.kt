package com.sample.recyclerview_advanced_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerview_advanced_sample.data.model.Contact
import com.sample.recyclerview_advanced_sample.databinding.ItemContactBinding

class ContactAdapter(
    private val onItemClicked: (Contact) -> Unit
) : ListAdapter<Contact, ContactAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(
        private val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.contact = contact
            binding.root.setOnClickListener {
                onItemClicked(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(currentList[position])

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem == newItem
        }
    }
}