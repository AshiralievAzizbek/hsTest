package com.example.hs_test.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hs_test.databinding.FragmentHomeBinding
import com.example.hs_test.util.whenStarted
import com.google.android.material.internal.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val mBinding get() = binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ProductsAdapter()
        mBinding.productsList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.productsList.adapter = adapter


        viewModel.productsFlow.onEach {
            adapter.submitList(it)
        }.whenStarted(lifecycleScope)

        setLayoutParams()
    }


    @SuppressLint("RestrictedApi")
    private fun setLayoutParams() {
        val tabs = mBinding.productsCategory.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginEnd = ViewUtils.dpToPx(requireContext(), 8).toInt()
            layoutParams.marginStart =
                if (i == 0) ViewUtils.dpToPx(requireContext(), 16).toInt() else 0
            tab.layoutParams = layoutParams
            tab.elevation = ViewUtils.dpToPx(requireContext(), 10)
            mBinding.productsCategory.requestLayout()
        }
    }
}