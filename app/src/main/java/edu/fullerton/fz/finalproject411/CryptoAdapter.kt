package edu.fullerton.fz.finalproject411

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.fullerton.fz.finalproject411.db.CryptoEntity

class CryptoAdapter(private val clickListener: (CryptoEntity) -> Unit) :
    ListAdapter<CryptoEntity, CryptoAdapter.CryptoViewHolder>(CryptoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.fragment_crypto_card, parent, false)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_crypto_card, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = getItem(position)
        holder.bind(crypto, clickListener)
    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cryptoNameTextView: TextView = itemView.findViewById(R.id.crypto_name)
        private val cryptoPriceTextView: TextView = itemView.findViewById(R.id.crypto_price)
        private val cryptoPercentChangeTextView: TextView = itemView.findViewById(R.id.crypto_percent_change)
        private val cryptoVolumeTextView: TextView = itemView.findViewById(R.id.crypto_volume)

        fun bind(crypto: CryptoEntity, clickListener: (CryptoEntity) -> Unit) {
            cryptoNameTextView.text = crypto.name
            cryptoPriceTextView.text = "Price: $${crypto.price}"
            cryptoPercentChangeTextView.text = "24h % Change: ${crypto.percentChange24h}%"
            cryptoVolumeTextView.text = "24h Volume: $${crypto.volume24h}"

        }
    }

    class CryptoDiffCallback : DiffUtil.ItemCallback<CryptoEntity>() {
        override fun areItemsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CryptoEntity, newItem: CryptoEntity): Boolean {
            return oldItem == newItem
        }
    }
}


