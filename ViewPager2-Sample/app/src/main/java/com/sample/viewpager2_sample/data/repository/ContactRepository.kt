package com.sample.viewpager2_sample.data.repository

import androidx.lifecycle.LiveData
import com.sample.viewpager2_sample.data.model.Contact

interface ContactRepository {

    fun getAll() : LiveData<List<Contact>>

    fun addContact(contact: Contact)

}