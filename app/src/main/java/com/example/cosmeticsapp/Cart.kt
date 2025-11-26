package com.example.cosmeticsapp

object Cart {
    private val items = mutableListOf<Product>()

    fun add(product: Product) { items.add(product) }
    fun remove(product: Product) { items.remove(product) }
    fun clear() = items.clear()
    fun all(): List<Product> = items.toList()
    fun total(): Double = items.sumOf { it.price }
}