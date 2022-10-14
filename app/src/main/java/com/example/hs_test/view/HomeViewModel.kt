package com.example.hs_test.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hs_test.data.model.Product
import com.example.hs_test.data.local.LocalRepository
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
            localRepository.insertProducts(getData())

        }
    }

    private fun getData(): List<Product> {
        return listOf(
            Product(id = 0, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 1, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 2, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 3, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 4, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 5, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 6, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
            Product(id = 7, name = "Pepperoni", "Lorem ipsum dolor paday skatina etakay", 777, "https://avatars.mds.yandex.net/i?id=0a33db4e5f13d546ce72bc8638021d8c_l-5115981-images-thumbs&n=13"),
        )
    }
}