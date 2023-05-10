package edu.fullerton.fz.finalproject411
//Favorites.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.fullerton.fz.finalproject411.databinding.FragmentFavoritesBinding
import edu.fullerton.fz.finalproject411.db.CryptoEntity

class Favorites : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var cryptoViewModel: CryptoViewModel
    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        cryptoAdapter = CryptoAdapter { crypto -> cryptoViewModel.toggleFavorite(crypto) }
        binding.recyclerView.apply {
            adapter = cryptoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        cryptoViewModel.favoriteCryptos.observe(viewLifecycleOwner) { cryptos ->
            cryptoAdapter.submitList(cryptos)
        }
    }
}


