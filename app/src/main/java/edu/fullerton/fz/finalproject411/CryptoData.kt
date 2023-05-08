package edu.fullerton.fz.finalproject411

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoData(
    val name: String,
    val price: Double,
    val percentChange24h: Double,
    val volume24h: Double
) : Parcelable
