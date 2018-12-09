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

package com.example.espresso.contrib.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espresso.contrib.activity.DetailActivity;
import com.example.espresso.contrib.databinding.AdapterItemBinding;
import com.example.espresso.contrib.model.DataItem;

/**
 * Simple adapter
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.BindingHolder> {

    private DataItem[] dataItems = new DataItem[50];
    private ClickListener clickListener = new ClickListener();

    public MainAdapter() {
        int count = dataItems.length;
        for (int i = 0; i < count; ++i) {
            dataItems[i] = new DataItem("Item " + (i + 1));
        }
    }

    @Override
    public int getItemCount() {
        return dataItems.length;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterItemBinding binding = AdapterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setListener(clickListener);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        AdapterItemBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (binding != null) {
            binding.setDataItem(dataItems[position]);
        }
    }


    /* package */ static class BindingHolder extends RecyclerView.ViewHolder {

        BindingHolder(@NonNull AdapterItemBinding binding) {
            super(binding.getRoot());
        }
    }

    public static class ClickListener {

        public void onItemClick(@NonNull View view, @NonNull DataItem dataItem) {
            DetailActivity.startActivity(view.getContext(), dataItem);
        }
    }
}
