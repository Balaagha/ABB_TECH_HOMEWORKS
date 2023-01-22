package com.example.todoapp.data.feature.download.repository

import com.example.todoapp.data.base.models.DataWrapper
import okhttp3.ResponseBody
import retrofit2.Response

interface DownloadOperationRepository {
    suspend fun downloadSampleImage(): DataWrapper<Response<ResponseBody>>
}