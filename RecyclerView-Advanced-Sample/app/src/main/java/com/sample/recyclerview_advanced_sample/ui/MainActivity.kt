package com.sample.recyclerview_advanced_sample.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerview_advanced_sample.databinding.ActivityMainBinding
import com.sample.recyclerview_advanced_sample.ui.adapter.ContactAdapter
import com.sample.recyclerview_advanced_sample.ui.viewmodel.ContactViewModel
import com.sample.recyclerview_advanced_sample.util.EventResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private val contactViewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewModel = contactViewModel
            lifecycleOwner = this@MainActivity
        }

        contactViewModel.eventResponse.observe(this) {
            when (it) {
                EventResponse.FAIL -> {
                    Toast.makeText(this, "모든 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                EventResponse.SUCCESS -> {
                    Toast.makeText(this, "정상적으로 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    hideKeyboard()
                }
            }
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        contactAdapter = ContactAdapter(
            onItemClicked = {
                Toast.makeText(this, "${it.name} : ${it.phone}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        setItemSwipeToLeft()
    }

    private fun setItemSwipeToLeft() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val target = contactAdapter.currentList[position]

                target?.let {
                    contactViewModel.deleteContact(it)
                    Toast.makeText(this@MainActivity, "정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.phoneEditText.windowToken, 0)
    }
}