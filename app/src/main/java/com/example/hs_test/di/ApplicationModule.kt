package com.example.hs_test.di

import android.content.Context
import androidx.room.Room
import com.example.hs_test.data.local.AppDatabase
import com.example.hs_test.data.local.ProductDao
import com.example.hs_test.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            val request = it.request().newBuilder()
                .header("X-RapidAPI-Key", "63174e8842mshbfa2d617a2086e8p1c54cdjsn8ec721cf099e")
                .header("X-RapidAPI-Host", "pizza-and-desserts.p.rapidapi.com")
                .build()
            return@Interceptor it.proceed(request)
        })
        .addInterceptor(getHttpLoggingInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://pizza-and-desserts.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "test_app_dabase").build()

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.userDao()
}

