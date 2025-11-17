package com.kaushik.mynewsapp.di

import com.kaushik.mynewsapp.common.Constants.BASE_URL
import com.kaushik.mynewsapp.data.remote.NewsApi
import com.kaushik.mynewsapp.data.repository.NewsRepositoryImpl
import com.kaushik.mynewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(NewsApi::class.java)

    }

    @Provides
    @Singleton
    fun providesNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api = api)
    }
}