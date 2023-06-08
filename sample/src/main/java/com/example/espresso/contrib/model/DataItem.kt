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
package com.example.espresso.contrib.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Simple model item
 */
class DataItem(val id: Int) : Parcelable {

    val title: String = "Item " + (id + 1)
    var favorite: Boolean = false
        private set

    fun toggleFavorite() {
        favorite = !favorite
    }

    constructor(inValue: Parcel) : this(
        inValue.readInt()
    ) {
        favorite = inValue.readInt() != 0
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeInt(if (favorite) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<DataItem?> {
        override fun createFromParcel(`in`: Parcel): DataItem {
            return DataItem(`in`)
        }

        override fun newArray(size: Int): Array<DataItem?> {
            return arrayOfNulls(size)
        }
    }
}