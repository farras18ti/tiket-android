package com.kel2.projectuas.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class User(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null
) : Parcelable