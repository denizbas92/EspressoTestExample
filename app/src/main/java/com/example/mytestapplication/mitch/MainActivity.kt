package com.example.mytestapplication.mitch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapplication.R
import com.example.mytestapplication.databinding.ActivityMainBinding
import com.example.mytestapplication.mitch.adapter.RecListAdapter

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,SecondaryActivity::class.java))
        }

        setAdapter()
    }

    private fun setAdapter() {
        val recListAdapter = RecListAdapter(this,getData())
        binding.recList.layoutManager = LinearLayoutManager(this)
        binding.recList.adapter = recListAdapter
    }

    private fun getData(): List<String> {
        return listOf(
            "A1",
            "A2",
            "A3",
            "A4",
            "A5",
            "A6",
            "A7",
            "A8",
            "A9",
            "A10",
            "A11",
            "A12",
            "A13",
            "A14",
            "A15",
            "A16",
            "A17"
        )
    }
}