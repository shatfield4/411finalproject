//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.fragment_crypto_card.view.*
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
//        val view = inflater.inflate(R.layout.fragment_crypto_card, container, false)
//        view.crypto_name.text = cryptoData.name
//        view.crypto_price.text = getString(R.string.crypto_price, cryptoData.price)
//        view.crypto_percent_change.text = getString(R.string.crypto_percent_change, cryptoData.percentChange24h)
//        view.crypto_volume.text = getString(R.string.crypto_volume, cryptoData.volume24h)
//        return view
//    }
//
//    companion object {
//        private const val ARG_CRYPTO_DATA = "cryptoData"
//
//        fun newInstance(cryptoData: CryptoData): CryptoCardFragment {
//            val args = Bundle().apply { putParcelable(ARG_CRYPTO_DATA, cryptoData) }
//            return CryptoCardFragment().apply { arguments = args }
//        }
//    }
//}
