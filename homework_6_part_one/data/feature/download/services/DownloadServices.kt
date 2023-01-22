package com.example.todoapp.data.feature.download.services

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Streaming

interface DownloadServices {

    @Streaming
    @GET("/img/Sample-jpg-image-5mb.jpg")
    suspend fun downloadSampleImage(): Response<ResponseBody>

}
