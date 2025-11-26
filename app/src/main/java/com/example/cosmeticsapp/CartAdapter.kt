package com.example.cosmeticsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    val items: MutableList<Product>,
    val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.ivCart)
        val name: TextView = view.findViewById(R.id.tvCartName)
        val price: TextView = view.findViewById(R.id.tvCartPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.iv.setImageResource(p.imageRes)
        holder.name.text = p.name
        holder.price.text = "${String.format("%.2f", p.price)} DH"
        holder.itemView.setOnClickListener {
            onItemClick(p)
        }
    }

    override fun getItemCount(): Int = items.size
}
