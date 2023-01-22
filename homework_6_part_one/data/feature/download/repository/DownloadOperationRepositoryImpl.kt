package com.example.todoapp.data.feature.download.repository

import com.example.todoapp.data.base.models.DataWrapper
import com.example.todoapp.data.base.repository.BaseRepository
import com.example.todoapp.data.feature.download.services.DownloadServices
import com.example.todoapp.data.feature.download.usecase.DownloadSampleImageUseCase
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class DownloadOperationRepositoryImpl @Inject constructor(
    val downloadServices: DownloadServices
) : BaseRepository(), DownloadOperationRepository {

    override suspend fun downloadSampleImage(): DataWrapper<Response<ResponseBody>> {
        return safeApiCall {
            downloadServices.downloadSampleImage()
        }
    }

}