package com.kaushik.mynewsapp.domain.usecase

import com.kaushik.mynewsapp.common.Resource
import com.kaushik.mynewsapp.data.remote.dto.TopHeadLines
import com.kaushik.mynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHeadLinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(countryCode: String): Flow<Resource<TopHeadLines>> = flow {

        try {
            val headLines = newsRepository.getHeadLineNews(code = countryCode)
            emit(Resource.Success(data = headLines))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }.flowOn(Dispatchers.IO)

}