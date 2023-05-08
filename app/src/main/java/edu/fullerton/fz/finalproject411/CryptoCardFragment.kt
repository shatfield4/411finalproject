//package edu.fullerton.fz.finalproject411
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.os.Parcelable
//import edu.fullerton.fz.finalproject411.databinding.FragmentCryptoCardBinding
//
//
//class CryptoCardFragment : Fragment() {
//
//    private lateinit var cryptoData: CryptoData
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            cryptoData = it.getParcelable(ARG_CRYPTO_DATA)!!
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding = FragmentCryptoCardBinding.inflate(inflater, container, false)
//        binding.cryptoName.text = cryptoData.name
//        binding.cryptoPrice.text = getString(R.string.crypto_price, cryptoData.price)
//        binding.cryptoPercentChange.text = getString(R.string.crypto_percent_change, cryptoData.percentChange24h)
//        binding.cryptoVolume.text = getString(R.string.crypto_volume, cryptoData.volume24h)
//        return binding.root
//    }
//
//    companion object {
//        private const val ARG_CRYPTO_DATA = "cryptoData"
//
//        fun newInstance(cryptoData: CryptoData): CryptoCardFragment {
//            val fragment = CryptoCardFragment()
//            val args = Bundle().apply {
//                putParcelable(ARG_CRYPTO_DATA, cryptoData)
//            }
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}
