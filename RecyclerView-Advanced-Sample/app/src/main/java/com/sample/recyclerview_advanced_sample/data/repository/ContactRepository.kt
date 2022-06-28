package com.sample.recyclerview_advanced_sample.data.repository

import androidx.lifecycle.LiveData
import com.sample.recyclerview_advanced_sample.data.model.Contact

interface ContactRepository {

    fun getAll(): LiveData<ArrayList<Contact>>

    fun addContact(contact: Contact)

    fun deleteContact(contact: Contact)
}