package com.afdhal_studio.foody.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.afdhal_studio.foody.R
import com.afdhal_studio.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.afdhal_studio.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.afdhal_studio.foody.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.recipes_bottom_sheet.view.*
import java.util.*


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mView = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) {
            mealTypeChip = it.selectedMealType
            dietTypeChip = it.selectedDietType

            updateChip(it.selectedMealTypeId, mView.mealType_chipGroup)
            updateChip(it.selectedDietTypeId, mView.dietType_chipGroup)
        }

        mView.mealType_chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.getDefault())
            mealTypeChip = selectedMealType
            mealTypeChipId = checkedId
        }

        mView.dietType_chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.getDefault())
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedId
        }

        mView.apply_btn.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )

            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }

        return mView
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.d("RecipesBottomSheet", e.message.toString())
            }
        }
    }

}