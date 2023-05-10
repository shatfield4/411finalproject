package edu.fullerton.fz.finalproject411

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import android.util.Log
import android.widget.LinearLayout
import okhttp3.*
import okio.IOException
import java.util.ArrayList



class Home : Fragment() {

    private val httpClient = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        fetchCryptoData()
        return view
    }
    private fun fetchCryptoData() {
        // Replace with your CoinMarketCap API key
        val apiKey = "191acd18-be56-4c37-a1c4-72b96366130e"
        val request = Request.Builder()
            .url("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10")
            .addHeader("X-CMC_PRO_API_KEY", apiKey)
            .build()

        Log.d("API", "Requesting CMC API")

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body!!.string()).getJSONArray("data")
                    val cryptoList = ArrayList<CryptoData>()
                    val length = json.length()
                    var i = 0
                    while (i < json.length()) {
                        val item = json.getJSONObject(i)
                        val cryptoData = CryptoData(
                            name = item.getString("name"),
                            price = item.getJSONObject("quote").getJSONObject("USD").getDouble("price"),
                            percentChange24h = item.getJSONObject("quote").getJSONObject("USD").getDouble("percent_change_24h"),
                            volume24h = item.getJSONObject("quote").getJSONObject("USD").getDouble("volume_24h")
                        )
                        cryptoList.add(cryptoData)
                        i++
                    }

                    activity?.runOnUiThread {
                        val fragmentManager = childFragmentManager
                        val cryptoCardContainer = view?.findViewById<LinearLayout>(R.id.crypto_card_container)
                        for (cryptoData in cryptoList) {
                            val cryptoCardFragment = CryptoCardFragment.newInstance(cryptoData)
                            fragmentManager.commit {
                                add(cryptoCardContainer!!.id, cryptoCardFragment)
                            }
                        }
                    }
                }
            }
        })
    }








}
