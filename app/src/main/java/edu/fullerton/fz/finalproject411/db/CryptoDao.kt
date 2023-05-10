package edu.fullerton.fz.finalproject411.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_data WHERE isFavorite = 1")
    fun getFavoriteCryptos(): Flow<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crypto: CryptoEntity)

    @Query("DELETE FROM crypto_data WHERE name = :name")
    suspend fun delete(name: String): Unit

    @Query("UPDATE crypto_data SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateFavorite(name: String, isFavorite: Boolean)

    @Query("UPDATE crypto_data SET price = :price, percentChange24h = :percentChange24h, volume24h = :volume24h WHERE name = :name")
    suspend fun updateCryptoData(name: String, price: Double, percentChange24h: Double, volume24h: Double)
}
