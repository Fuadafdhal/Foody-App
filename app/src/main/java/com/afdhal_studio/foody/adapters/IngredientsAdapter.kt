package com.afdhal_studio.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.afdhal_studio.foody.R
import com.afdhal_studio.foody.models.ExtendedIngredient
import com.afdhal_studio.foody.util.Constants.Companion.BASE_IMAGE_URL
import com.afdhal_studio.foody.util.DiffUtils
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*
import java.util.*

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.VHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(ingredients: ExtendedIngredient) {
            with(itemView) {
                itemView.ingredient_imageView.load(BASE_IMAGE_URL + ingredients.image) {
                    crossfade(600)
                    error(R.drawable.ic_error_placeholder)
                }
                ingredient_name.text = ingredients.name.capitalize(Locale.ROOT)
                ingredient_amount.text = ingredients.amount.toString()
                ingredient_unit.text = ingredients.unit
                ingredient_consistency.text = ingredients.consistency
                ingredient_original.text = ingredients.original
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredients_row_layout, parent, false)
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