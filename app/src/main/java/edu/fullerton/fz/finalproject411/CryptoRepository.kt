package edu.fullerton.fz.finalproject411

import android.content.Context
import edu.fullerton.fz.finalproject411.db.AppDatabase
import edu.fullerton.fz.finalproject411.db.CryptoDao
import edu.fullerton.fz.finalproject411.db.CryptoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class CryptoRepository(private val cryptoDao: CryptoDao) {

    fun getFavoriteCryptos(): Flow<List<CryptoEntity>> = cryptoDao.getFavoriteCryptos()

    suspend fun setFavorite(cryptoData: CryptoData, isFavorite: Boolean) = withContext(IO) {

        val cryptoEntity = CryptoEntity(
            name = cryptoData.name,
            price = cryptoData.price,
            percentChange24h = cryptoData.percentChange24h,
            volume24h = cryptoData.volume24h,
            isFavorite = isFavorite
        )

        if (isFavorite) {
            cryptoDao.insert(cryptoEntity)
        } else {
            cryptoDao.updateFavorite(cryptoEntity.name, isFavorite)
        }
        cryptoDao.updateCryptoData(cryptoEntity.name, cryptoEntity.price, cryptoEntity.percentChange24h, cryptoEntity.volume24h)

    }


    companion object {
        private var INSTANCE: CryptoRepository? = null

        fun getRepository(context: Context): CryptoRepository {
            return INSTANCE ?: synchronized(this) {
                val database = AppDatabase.getDatabase(context)
                val instance = CryptoRepository(database.cryptoDao())
                INSTANCE = instance
                instance
            }
        }
    }
}

