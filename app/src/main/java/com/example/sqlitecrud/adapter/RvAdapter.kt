package com.example.sqlitecrud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitecrud.databinding.ItemRvBinding
import com.example.sqlitecrud.models.User

class RvAdapter( var list: ArrayList<User>) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class  Vh( var itemRv: ItemRvBinding):RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User) {
            itemRv.itemName.text = user.name
            itemRv.itemNumber.text = user.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}