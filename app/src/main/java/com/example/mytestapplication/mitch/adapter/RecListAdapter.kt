package com.example.mytestapplication.mitch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.mytestapplication.R
import com.example.mytestapplication.databinding.RecListItemBinding
import com.example.mytestapplication.mitch.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher


class RecListAdapter(private val mainActivity: MainActivity, private val data: List<String>) : RecyclerView.Adapter<RecListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: RecListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.rec_list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setBind(mainActivity,data[position])
    }

    class MyViewHolder(private val binding: RecListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setBind(mainActivity: MainActivity, item: String) {
            binding.tvData.text = item

            binding.tvData.setOnClickListener {
                println("Clicked : " + binding.tvData.text)
            }
        }

    }

    fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}