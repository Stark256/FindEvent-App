package com.esketit.myapp.ui.base

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V: RecyclerView.ViewHolder>: RecyclerView.Adapter<V>() {
    private var items = mutableListOf<T>()

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceAll(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addAll(list: List<T>){
        val from = items.size
        items.addAll(list)

        val to = items.size

        notifyItemRangeChanged(from, to)
    }

    fun add(item: T){
        items.add(item)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    fun getItems(): List<T> {
        return items
    }

    fun removeItem(item: T) {
        items.remove(item)
        notifyDataSetChanged()
    }

    fun setItem(item: T, index: Int) {
        items.set(index, item)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}