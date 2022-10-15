package com.example.hs_test.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hs_test.data.local.LocalRepository
import com.example.hs_test.data.model.Product
import com.example.hs_test.data.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    val productsFlow =
        localRepository.getProducts().shareIn(viewModelScope, SharingStarted.Eagerly, 1)


    init {
        viewModelScope.launch {
            val products: List<Product> =
                remoteRepository.getProducts()?.products?.first()?.products ?: listOf()
            localRepository.insertProducts(products)
        }
    }

}