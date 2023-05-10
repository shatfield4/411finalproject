package edu.fullerton.fz.finalproject411

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.*
import edu.fullerton.fz.finalproject411.db.CryptoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private val cryptoRepository = CryptoRepository.getRepository(application)


    val favoriteCryptos: LiveData<List<CryptoEntity>> =
        cryptoRepository.getFavoriteCryptos().asLiveData()



    fun toggleFavorite(crypto: CryptoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = crypto.isFavorite
            cryptoRepository.setFavorite(crypto, !isFavorite)
        }
    }
}

