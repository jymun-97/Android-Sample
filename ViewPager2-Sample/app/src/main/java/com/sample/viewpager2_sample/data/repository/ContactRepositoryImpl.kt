package com.sample.viewpager2_sample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.viewpager2_sample.data.model.Contact

class ContactRepositoryImpl : ContactRepository {

    private val data = mutableListOf(
        Contact("Moon", "010-5764-5123"),
        Contact("Hong", "010-0000-0000"),
        Contact("Kim", "010-1111-1111"),
        Contact("Park", "010-2222-2222")
    )

    private val _contacts = MutableLiveData<List<Contact>>(data)
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    override fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    override fun addContact(contact: Contact) {
        data.add(contact).also { _contacts.postValue(data) }
    }
}