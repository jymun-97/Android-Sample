package com.sample.recyclerview_advanced_sample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.recyclerview_advanced_sample.data.model.Contact
import com.sample.recyclerview_advanced_sample.data.repository.ContactRepositoryImpl
import com.sample.recyclerview_advanced_sample.util.EventResponse

class ContactViewModel : ViewModel() {

    private val repo = ContactRepositoryImpl()

    val contacts: LiveData<ArrayList<Contact>>
        get() = repo.getAll()

    val inputName = MutableLiveData<String?>()
    val inputPhone = MutableLiveData<String?>()
    val eventResponse = MutableLiveData<EventResponse>()

    fun addItem() {
        if (inputName.value.isNullOrEmpty() || inputPhone.value.isNullOrEmpty()) {
            eventResponse.value = EventResponse.FAIL
            return
        }
        val newContact = Contact(
            name = inputName.value!!,
            phone = convertPhone(inputPhone.value!!)
        )
        repo.addContact(newContact)
        eventResponse.value = EventResponse.SUCCESS
        clearInput()
    }

    fun deleteContact(contact: Contact) {
        repo.deleteContact(contact)
    }

    private fun convertPhone(phone: String): String {
        return StringBuilder(phone.substring(0, 3))
            .append('-')
            .append(phone.substring(3, 7))
            .append('-')
            .append(phone.substring(7))
            .toString()
    }

    private fun clearInput() {
        inputName.postValue(null)
        inputPhone.postValue(null)
    }
}