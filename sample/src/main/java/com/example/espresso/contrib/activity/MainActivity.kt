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

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.espresso.contrib.R
import com.example.espresso.contrib.adapter.MainAdapter
import com.example.espresso.contrib.databinding.ActivityMainBinding
import com.example.espresso.contrib.ktx.doOnApplyWindowInsetsToPadding
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = MainAdapter()

        binding.appBar.doOnApplyWindowInsetsToPadding { view, windowInsetsCompat, initialPadding ->
            val bars = windowInsetsCompat.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout()
            )
            view.updatePadding(
                top = initialPadding.top + bars.top,
                left = initialPadding.left + bars.left,
                right = initialPadding.right + bars.right,
            )
        }

        binding.recyclerView.doOnApplyWindowInsetsToPadding { view, windowInsetsCompat, initialPadding ->
            val bars = windowInsetsCompat.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout()
            )

            view.updatePadding(
                left = initialPadding.left + bars.left,
                right = initialPadding.right + bars.right,
            )
        }
    }
}