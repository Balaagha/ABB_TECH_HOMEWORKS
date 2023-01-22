package com.example.todoapp.data.base.repository

import android.util.Log
import com.example.todoapp.data.base.models.DataWrapper
import com.example.data.base.models.ErrorResponse
import com.example.data.base.models.FailureType
import com.example.data.base.models.FetchType
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseRepository {



    inline fun <reified T> safeApiCall(
        jsonAssetPath: String? = null,
        fetchType: FetchType? = null,
        apiCall: () -> Response<T>
    ): DataWrapper<Response<T>> {
        return try {
            val response = apiCall.invoke()
            DataWrapper.Success(
                value = response
            )
        } catch (throwable: Throwable) {
            Log.d("myTag","ERROR => ${throwable.localizedMessage}")
            handleApiCallException(throwable)
        }
    }

    fun <T> handleApiCallException(throwable: Throwable): DataWrapper<T> {
        when (throwable) {
            is SocketTimeoutException -> {
                return DataWrapper.Failure(FailureType.RESPONSE_TIMEOUT)
            }
            is UnknownHostException -> {
                return DataWrapper.Failure(FailureType.NO_INTERNET_CONNECTION)
            }

            is ConnectException -> {
                return DataWrapper.Failure(FailureType.NO_INTERNET_CONNECTION)
            }
            is HttpException -> {
                when {
                    throwable.code() == 401 -> {
                        val errorResponse = Gson().fromJson(
                            throwable.response()?.errorBody()!!.charStream().readText(),
                            ErrorResponse::class.java
                        )
                        return DataWrapper.Failure(
                            failureType = FailureType.HTTP_EXCEPTION,
                            code = throwable.code(),
                            message = errorResponse.detail
                        )
                    }
                    else -> {
                        return if (throwable.response()?.errorBody()!!.charStream().readText()
                                .isEmpty()
                        ) {
                            DataWrapper.Failure(
                                failureType = FailureType.HTTP_EXCEPTION,
                                code = throwable.code()
                            )
                        } else {
                            try {
                                val errorResponse = Gson().fromJson(
                                    throwable.response()?.errorBody()!!.charStream().readText(),
                                    ErrorResponse::class.java
                                )

                                DataWrapper.Failure(
                                    failureType = FailureType.HTTP_EXCEPTION,
                                    code = throwable.code(),
                                    message = errorResponse.detail
                                )
                            } catch (ex: JsonSyntaxException) {
                                DataWrapper.Failure(
                                    failureType = FailureType.HTTP_EXCEPTION,
                                    code = throwable.code()
                                )
                            }
                        }
                    }
                }
            }
            else -> {
                return DataWrapper.Failure(FailureType.OTHER)
            }
        }
    }


}