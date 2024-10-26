// ShoppingItem.kt
package com.example.green_snack

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingItem(
    val name: String,
    var isChecked: Boolean
) : Parcelable
