package edu.fullerton.fz.finalproject411

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
) : Parcelable {
    fun toSharedPrefsString(): String {
        return "$name,$price,$percentChange24h,$volume24h,$isFavorite"
    }

    companion object {
        fun fromSharedPrefsString(string: String): CryptoData {
            val parts = string.split(",")
            return CryptoData(
                parts[0],
                parts[1].toDouble(),
                parts[2].toDouble(),
                parts[3].toDouble(),
                parts[4].toBoolean()
            )
        }
    }
}

