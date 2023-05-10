package edu.fullerton.fz.finalproject411.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_data")
data class CryptoEntity(
    @PrimaryKey val name: String,
    val price: Double,
    val percentChange24h: Double,
    val volume24h: Double,
    var isFavorite: Boolean = false
)
