package com.lucianoluzzi.pokemons.details.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.databinding.ListItemEvolutionBinding
import com.lucianoluzzi.pokemons.details.domain.entity.Evolution

class EvolutionsAdapter(private val evolutions: List<Evolution>) :
    RecyclerView.Adapter<EvolutionsAdapter.EvolutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemEvolutionBinding.inflate(inflater)
        return EvolutionViewHolder(binding)
    }

    override fun getItemCount(): Int = evolutions.size

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) =
        holder.bind(evolutions[position])

    class EvolutionViewHolder(private val binding: ListItemEvolutionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(evolution: Evolution) {
            binding.image.load(evolution.image) {
                crossfade(true)
                placeholder(R.mipmap.ic_pokeball_foreground)
            }
        }
    }
}