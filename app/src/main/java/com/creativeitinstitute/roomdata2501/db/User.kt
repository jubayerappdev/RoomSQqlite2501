package com.creativeitinstitute.roomdata2501.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val age: Int,
    val mobile: String
): Parcelable
