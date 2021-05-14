package com.afdhal_studio.foody.ui.fragments.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_studio.foody.adapters.IngredientsAdapter
import com.afdhal_studio.foody.databinding.FragmentIngredientsBinding
import com.afdhal_studio.foody.models.Result
import com.afdhal_studio.foody.util.Constants.Companion.RECIPE_RESULT_KEY


class IngredientsFragment : Fragment() {
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!
    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val mBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)
        val extendedIngredients = mBundle?.extendedIngredients
        if (extendedIngredients != null) mAdapter.setData(extendedIngredients)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding.ingredientsRecyclerview) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}