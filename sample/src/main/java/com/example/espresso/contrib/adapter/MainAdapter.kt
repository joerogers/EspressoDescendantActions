/*
 * Copyright 2016 Joe Rogers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.espresso.contrib.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.espresso.contrib.activity.DetailActivity
import com.example.espresso.contrib.adapter.MainAdapter.BindingHolder
import com.example.espresso.contrib.databinding.AdapterItemBinding
import com.example.espresso.contrib.model.DataItem

/**
 * Simple adapter
 */
class MainAdapter : RecyclerView.Adapter<BindingHolder>() {
    private val dataItems = mutableListOf<DataItem>()

    // This list will not survive rotation. Just a dummy list for the
    // purpose of demoing the library. In a proper app this would be
    // maintained by a ViewHolder or other equivalent object
    init {
        val count = 50
        for (i in 0 until count) {
            dataItems.add(DataItem(i))
        }
        setHasStableIds(true)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = AdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.itemClickListener = ItemClickListener()
        binding.favoriteClickListener = FavoriteClickListener()
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.dataItem = dataItems[position]
    }

    class BindingHolder(val binding: AdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ItemClickListener {
        fun onClick(view: View, dataItem: DataItem) {
            DetailActivity.startActivity(view.context, dataItem)
        }
    }

    inner class FavoriteClickListener {
        fun onClick(dataItem: DataItem) {
            dataItem.toggleFavorite()
            // id = position only because list doesn't change...
            notifyItemChanged(dataItem.id)
        }
    }
}