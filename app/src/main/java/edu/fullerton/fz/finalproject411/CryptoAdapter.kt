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
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.fragment_crypto_card, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = getItem(position)
        holder.bind(crypto, clickListener)
    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cryptoNameTextView: TextView = itemView.findViewById(R.id.crypto_name)

        fun bind(crypto: CryptoEntity, clickListener: (CryptoEntity) -> Unit) {
            cryptoNameTextView.text = crypto.name
            itemView.setOnClickListener { clickListener(crypto) }
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


