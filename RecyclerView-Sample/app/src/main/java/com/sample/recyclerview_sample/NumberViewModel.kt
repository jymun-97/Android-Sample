package com.sample.recyclerview_sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberViewModel: ViewModel() {

    private val data = mutableListOf<Number>()

    val numbers = MutableLiveData<List<Number>>(emptyList())
    val count = MutableLiveData(0)

    fun add() {
        val newItem = Number(data.size + 1)
        data.add(newItem)

        numbers.postValue(data)
        count.postValue(data.size)
    }
}