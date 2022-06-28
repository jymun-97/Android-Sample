package com.sample.recyclerview_advanced_sample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.recyclerview_advanced_sample.data.model.Contact

class ContactRepositoryImpl : ContactRepository {

    private val data = arrayListOf(
        Contact("Moon", "010-5764-5123"),
        Contact("Hong", "010-0000-0000"),
        Contact("Kim", "010-1234-5678")
    )

    private val _contacts = MutableLiveData(data)
    val contacts: LiveData<ArrayList<Contact>>
        get() = _contacts

    override fun getAll(): LiveData<ArrayList<Contact>> = contacts

    override fun addContact(contact: Contact) {
        data.add(contact).also { _contacts.postValue(data) }
    }

    override fun deleteContact(contact: Contact) {
        data.remove(contact).also { _contacts.postValue(data) }
    }


}