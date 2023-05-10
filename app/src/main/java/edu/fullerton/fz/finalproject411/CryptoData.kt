package edu.fullerton.fz.finalproject411
//CryptoData.kt
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.descriptors.PrimitiveKind

@Parcelize
data class CryptoData(
    val name: String,
    val price: Double,
    val percentChange24h: Double,
    val volume24h: Double,
    var isFavorite: Boolean = false
) : Parcelable

