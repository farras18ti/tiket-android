package com.kel2.projectuas.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tiket(
    val id: String? = "",
    val jumlah_kursi: String? = "",
    val total: String? = "",
    val lokasi: String? = "",
    val id_user: String? = "",
    val id_film: String? = ""
) : Parcelable