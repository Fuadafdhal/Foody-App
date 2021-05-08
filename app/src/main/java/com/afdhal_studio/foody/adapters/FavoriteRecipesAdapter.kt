package com.afdhal_studio.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afdhal_studio.foody.data.database.entities.FavoritesEntity
import com.afdhal_studio.foody.databinding.FavoriteRecipesRowLayoutBinding
import com.afdhal_studio.foody.util.DiffUtils

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.VHolder>() {
    private var favoriteRecipes = emptyList<FavoritesEntity>()

    class VHolder(private val binding: FavoriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: FavoritesEntity) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): VHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return VHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(favoriteRecipes[position])
    }

    override fun getItemCount() = favoriteRecipes.size

    fun setData(newFavoriteRecipes: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil = DiffUtils(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

}