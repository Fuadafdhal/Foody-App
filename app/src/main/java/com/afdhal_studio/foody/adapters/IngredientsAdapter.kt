package com.afdhal_studio.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.afdhal_studio.foody.R
import com.afdhal_studio.foody.databinding.IngredientsRowLayoutBinding
import com.afdhal_studio.foody.models.ExtendedIngredient
import com.afdhal_studio.foody.util.Constants.Companion.BASE_IMAGE_URL
import com.afdhal_studio.foody.util.DiffUtils
import java.util.*

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.VHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    inner class VHolder(val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(ingredients: ExtendedIngredient) {
            binding.ingredientImageView.load(BASE_IMAGE_URL + ingredients.image) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            binding.ingredientName.text = ingredients.name.capitalize(Locale.ROOT)
            binding.ingredientAmount.text = ingredients.amount.toString()
            binding.ingredientUnit.text = ingredients.unit
            binding.ingredientConsistency.text = ingredients.consistency
            binding.ingredientOriginal.text = ingredients.original
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VHolder(
            IngredientsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IngredientsAdapter.VHolder, position: Int) {
        holder.onBind(ingredientsList[position])
    }

    override fun getItemCount() = ingredientsList.size

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = DiffUtils(ingredientsList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }
}