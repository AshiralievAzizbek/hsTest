package com.example.hs_test.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hs_test.data.model.Banner
import com.example.hs_test.databinding.FragmentHomeBinding
import com.example.hs_test.util.whenStarted
import com.example.hs_test.view.adapters.BannersAdapter
import com.example.hs_test.view.adapters.ProductsAdapter
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
        val productsAdapter = ProductsAdapter()
        mBinding.productsList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.productsList.adapter = productsAdapter

        val bannersAdapter = BannersAdapter()
        mBinding.bannersList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.bannersList.adapter = bannersAdapter
        bannersAdapter.submitList(getHardcodeBanners())

        viewModel.productsFlow.onEach {
            productsAdapter.submitList(it)
        }.whenStarted(lifecycleScope)

        setLayoutParams()

    }

    private fun getHardcodeBanners() = listOf(
        Banner(
            id = 0,
            url = "https://img.freepik.com/free-psd/fast-food-restaurant-social-media-promo-template_23-2149738618.jpg?size=626&ext=jpg"
        ),
        Banner(
            id = 1,
            url = "https://mir-s3-cdn-cf.behance.net/project_modules/1400/c436e4154440027.6342704c4677a.jpg"
        )
    )

    private fun setLayoutParams() {
        val tabs = mBinding.productsCategory.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginEnd = ViewUtils.dpToPx(requireContext(), 3).toInt()
            layoutParams.marginStart =
                if (i == 0) ViewUtils.dpToPx(requireContext(), 11).toInt() else 0
            layoutParams.height = ViewUtils.dpToPx(requireContext(), 42).toInt()
            tab.layoutParams = layoutParams
            mBinding.productsCategory.requestLayout()
        }
    }
}