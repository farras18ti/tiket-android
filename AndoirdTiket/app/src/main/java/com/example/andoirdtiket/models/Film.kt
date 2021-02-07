package com.example.andoirdtiket.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val harga: String,
    val id: String? = null,
    val nama_film: String,
    val lokasi: String? = ""
) : Parcelable