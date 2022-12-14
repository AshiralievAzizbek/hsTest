package com.example.hs_test.util

import android.widget.ImageView
import androidx.lifecycle.LifecycleCoroutineScope
import com.bumptech.glide.Glide
import com.example.hs_test.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.whenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@whenStarted.collect()
    }
}

fun ImageView.load(url: String?, onErrorImagePath: Int = R.drawable.img_pizza) {
    Glide.with(this).load(url).error(onErrorImagePath).into(this)
}
