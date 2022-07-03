package com.sample.viewpager2_sample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.viewpager2_sample.data.model.Contact
import com.sample.viewpager2_sample.data.repository.ContactRepositoryImpl
import java.lang.StringBuilder

class ContactViewModel : ViewModel() {

    private val repo = ContactRepositoryImpl()

    val contacts: LiveData<List<Contact>>
        get() = repo.getAll()

    val inputName = MutableLiveData<String?>()
    val inputPhone = MutableLiveData<String?>()
    val enable = MutableLiveData(false)

    fun addContact() {
        repo.addContact(
            Contact(
                name = inputName.value!!,
                phone = convertPhone(inputPhone.value!!)
            )
        )
    }

    fun onTextChanged() {
        enable.postValue(
            !inputName.value.isNullOrEmpty()
                    && !inputPhone.value.isNullOrEmpty()
        )
    }

    private fun convertPhone(phone: String) =
        StringBuilder()
            .append(phone.substring(0, 3))
            .append('-')
            .append(phone.substring(3, 7))
            .append('-')
            .append(phone.substring(7))
            .toString()
}