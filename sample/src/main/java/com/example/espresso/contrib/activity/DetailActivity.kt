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
package com.example.espresso.contrib.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.databinding.DataBindingUtil
import com.example.espresso.contrib.R
import com.example.espresso.contrib.databinding.ActivityDetailBinding
import com.example.espresso.contrib.model.DataItem

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        val dataItem = IntentCompat.getParcelableExtra(intent, EXTRA_DATA, DataItem::class.java)
        binding.dataItem = dataItem
    }

    companion object {
        const val EXTRA_DATA = "dataItem"

        fun startActivity(context: Context, dataItem: DataItem) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, dataItem)
            context.startActivity(intent)
        }
    }
}