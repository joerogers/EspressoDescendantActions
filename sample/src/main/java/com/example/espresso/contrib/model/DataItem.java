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

package com.example.espresso.contrib.model;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;

import com.forkingcode.espresso.contrib.sample.BR;


/**
 * Simple model item
 */
public class DataItem extends BaseObservable implements Parcelable {

    private String title;
    private boolean favorite;

    public DataItem(@Nullable String title) {
        this.title = title;
        this.favorite = false;
    }

    @Bindable
    @Nullable
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    public void setTitle(@Nullable String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
        notifyPropertyChanged(BR.favorite);
    }

    public void toggleFavorite() {
        setFavorite(!favorite);
    }

    @SuppressWarnings("WeakerAccess")
    protected DataItem(@NonNull Parcel in) {
        title = in.readString();
        favorite = in.readInt() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(favorite ? 1 : 0);
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}
