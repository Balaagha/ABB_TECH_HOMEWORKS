package com.example.todoapp.data.feature.download.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.todoapp.data.base.models.DataWrapper
import com.example.data.base.models.FailureBehavior
import com.example.data.base.models.FailureType
import com.example.todoapp.data.feature.download.repository.DownloadOperationRepository
import com.example.todoapp.utils.extentions.checkNetworkResultIsSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DownloadSampleImageUseCase @Inject constructor(
    private val repository: DownloadOperationRepository
) {
    suspend operator fun invoke(): Flow<DataWrapper<Bitmap>> = flow {
        emit(DataWrapper.Loading)
        try {
            val response = repository.downloadSampleImage()
            if (response is DataWrapper.Success<*> && checkNetworkResultIsSuccess(response.invoke())) {
                response.invoke()?.body()?.let {
                    it.byteStream().use { inputStream ->
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        emit(DataWrapper.Success(bitmap))
                    }
                } ?: kotlin.run {
                    emit(
                        DataWrapper.Failure<Bitmap>(
                            FailureType.OTHER,
                            FailureBehavior.ALERT,
                            message = "Null Body"
                        )
                    )
                }
            } else {
                emit(
                    DataWrapper.Failure<Bitmap>(
                        FailureType.API_GENERIC_ERROR,
                        FailureBehavior.ALERT,
                        message = "Error connection"
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                DataWrapper.Failure<Bitmap>(
                    FailureType.OTHER,
                    FailureBehavior.ALERT,
                    message = e.localizedMessage
                )
            )
        }
    }
}