package edu.fullerton.fz.finalproject411

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Parcelable
import edu.fullerton.fz.finalproject411.databinding.FragmentCryptoCardBinding
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import java.util.regex.Pattern
import android.graphics.PorterDuff
import android.content.Context
import android.view.animation.AnimationUtils
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.lifecycleScope





class CryptoCardFragment : Fragment() {

    private lateinit var cryptoData: CryptoData
    private val favoritesDataStore by lazy { FavoritesDataStore.getStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cryptoData = it.getParcelable(ARG_CRYPTO_DATA)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCryptoCardBinding.inflate(inflater, container, false)
        binding.cryptoName.text = cryptoData.name
        binding.cryptoPrice.text = getString(R.string.crypto_price, cryptoData.price)

        //we put cryptoPercentChange into a variable
        val percentChangeText = getString(R.string.crypto_percent_change, cryptoData.percentChange24h)
        binding.cryptoPercentChange.setTextColor(Color.WHITE)
        //using the function parser colorNumericPartOfString() we parse the string ignoring the first two characters then looking for numerical values
        binding.cryptoPercentChange.text = colorNumericPartOfString(percentChangeText, if (cryptoData.percentChange24h < 0) Color.RED else Color.GREEN)


        binding.cryptoVolume.text = getString(R.string.crypto_volume, cryptoData.volume24h)
        binding.cryptoPrice.setTextColor(Color.WHITE)
        binding.cryptoVolume.setTextColor(Color.WHITE)
        binding.star.setColorFilter(Color.WHITE)



        binding.star.setOnClickListener {
            // Toggle the favorite status of the crypto
            cryptoData.isFavorite = !cryptoData.isFavorite
            // Change the color of the star based on the new favorite status
            val sharedPrefs = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            if (cryptoData.isFavorite) {
                    println(cryptoData.name) //check name
                    println(cryptoData.isFavorite) //check true or false
                binding.star.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP)


                // Start the animation
                val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.star_scale)
                binding.star.startAnimation(animation)


            } else {
                    println(cryptoData.name)
                    println(cryptoData.isFavorite)
                binding.star.setColorFilter(Color.WHITE)

            }
        }

        favoritesDataStore.isFavorite(cryptoData.name).onEach { isFavorite ->
            cryptoData.isFavorite = isFavorite
            if (isFavorite) {
                binding.star.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP)
            } else {
                binding.star.setColorFilter(Color.WHITE)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        return binding.root
    }

    companion object {
        private const val ARG_CRYPTO_DATA = "cryptoData"

        fun newInstance(cryptoData: CryptoData): CryptoCardFragment {
            val args = Bundle().apply { putParcelable(ARG_CRYPTO_DATA, cryptoData) }
            return CryptoCardFragment().apply { arguments = args }
        }
    }


    //function that will parse for numerical values
    fun colorNumericPartOfString(input: String, color: Int): SpannableString {
        val pattern = Pattern.compile("(?<=..)\\d+\\.?\\d*") // Regex to match any number in the string, ignoring the first two characters
        val matcher = pattern.matcher(input)

        val spannableString = SpannableString(input)

        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            spannableString.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }

        return spannableString
    }

}
