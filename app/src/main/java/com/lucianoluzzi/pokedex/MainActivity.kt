package com.lucianoluzzi.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.PokemonListFragmentFactory
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = get() as PokemonListFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}