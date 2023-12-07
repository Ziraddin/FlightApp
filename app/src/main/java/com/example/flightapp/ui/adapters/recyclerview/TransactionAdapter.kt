package com.example.flightapp.ui.adapters.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightapp.databinding.ItemTransactionBinding
import com.example.flightapp.model.Transaction

class TransactionAdapter(private var transactions: MutableList<Transaction>, val nav: (Bundle) -> Unit) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    fun updateData(newTransactions: MutableList<Transaction>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transactionItem: Transaction) {
            binding.apply {
                tvTransactionName.text =
                    "Flight number : ".plus(transactionItem.flight.flightNumber)
                tvTransactionDate.text = "Date : ".plus(transactionItem.date.toString())
                tvTransactionAmount.text =
                    "Amount : ".plus(transactionItem.flight.price.toString()).plus(" $")
                btnTransactionDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("transaction",transactionItem)
                    nav.invoke(bundle)
                }
            }
        }
    }
}