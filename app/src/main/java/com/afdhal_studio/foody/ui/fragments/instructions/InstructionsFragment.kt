package com.afdhal_studio.foody.ui.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.afdhal_studio.foody.databinding.FragmentInstructionsBinding
import com.afdhal_studio.foody.models.Result
import com.afdhal_studio.foody.util.Constants


class InstructionsFragment : Fragment() {
    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val mBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        binding.instructionsWebView.webViewClient = object : WebViewClient() {}

        val websiteUrl: String = mBundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}