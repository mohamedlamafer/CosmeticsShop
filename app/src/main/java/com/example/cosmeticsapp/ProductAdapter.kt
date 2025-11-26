package com.example.cosmeticsapp

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val items: List<Product>,
    private val onClick: (Product) -> Unit,
    private val onLongClick: (Product) -> Boolean,
    private val onImageTouch: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.card)
        val iv: ImageView = view.findViewById(R.id.ivProduct)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.tvName.text = p.name
        holder.tvPrice.text = "${String.format("%.2f", p.price)} DH"
        holder.iv.setImageResource(p.imageRes)

        holder.card.setOnClickListener { onClick(p) }
        holder.card.setOnLongClickListener { onLongClick(p) }

        holder.iv.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.scaleX = 0.5f
                v.scaleY = 0.5f
            } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                v.scaleX = 1f
                v.scaleY = 1f
            }
            if (event.action == MotionEvent.ACTION_UP) onImageTouch(p)
            true
        }
    }

    override fun getItemCount(): Int = items.size
}
