package com.afdhal_studio.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_studio.foody.R
import com.afdhal_studio.foody.adapters.IngredientsAdapter
import com.afdhal_studio.foody.models.Result
import com.afdhal_studio.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_ingredients.view.*


class IngredientsFragment : Fragment() {
    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val mBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)
        val extendedIngredients = mBundle?.extendedIngredients
        if (extendedIngredients != null) mAdapter.setData(extendedIngredients)

        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        with(view.ingredients_recyclerview) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }
}