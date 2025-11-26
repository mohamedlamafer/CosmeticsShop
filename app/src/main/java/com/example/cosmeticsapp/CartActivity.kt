package com.example.cosmeticsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var tvTotal: TextView
    private lateinit var btnValidate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recycler = findViewById(R.id.recyclerCart)
        tvTotal = findViewById(R.id.tvTotal)
        btnValidate = findViewById(R.id.btnValidate)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter(Cart.all().toMutableList()) { product ->
            refresh()
        }
        recycler.adapter = adapter

        val itemTouch = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, direction: Int) {
                val pos = vh.adapterPosition
                val prod = adapter.items[pos]
                Cart.remove(prod)
                refresh()
                Toast.makeText(this@CartActivity, getString(R.string.removed), Toast.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(itemTouch).attachToRecyclerView(recycler)

        btnValidate.setOnClickListener {
            if (Cart.all().isEmpty()) {
                Toast.makeText(this, getString(R.string.cart_empty), Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, CheckoutActivity::class.java))
                Toast.makeText(this, getString(R.string.order_validated), Toast.LENGTH_LONG).show()
                Cart.clear()
                refresh()
            }
        }

        refresh()
    }

    private fun refresh() {
        val list = Cart.all().toMutableList()
        adapter.items.clear()
        adapter.items.addAll(list)
        adapter.notifyDataSetChanged()
        tvTotal.text = getString(R.string.total) + String.format("%.2f", Cart.total()) + "DH"
    }
}
