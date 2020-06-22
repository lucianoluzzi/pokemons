package com.lucianoluzzi.pokemons.details.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.lucianoluzzi.pokemons.databinding.ListItemTypeBinding
import com.lucianoluzzi.pokemons.details.domain.entity.Type

class TypesAdapter(private val types: List<Type>) :
    RecyclerView.Adapter<TypesAdapter.TypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTypeBinding.inflate(inflater)
        return TypeViewHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bind(types[position])
    }

    class TypeViewHolder(private val binding: ListItemTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(type: Type) {
            binding.type.load(type.typeIcon)
            binding.type.contentDescription = type.description
        }
    }
}