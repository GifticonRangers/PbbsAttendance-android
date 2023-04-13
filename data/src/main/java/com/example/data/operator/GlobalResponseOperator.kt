package com.example.data.operator

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.data.mapper.ErrorEnvelopeMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GlobalResponseOperator<T> constructor(
    private val success: suspend (ApiResponse.Success<T>) -> Unit,
    private val application: Application
) : ApiResponseSuspendOperator<T>() {

    // The body is empty, because we will handle the success case manually.
    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = success(apiResponse)

    // handles error cases when the API request gets an error response.
    // e.g., internal server error.
    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Log.d("GlobalResponseOperator.onError.apiResponse::",message())

                // handling error based on status code.
                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else ->{
                        Log.d("GlobalResponseOperator.onError.statusCode::","[Code: $statusCode.code]: ${message()}")
                        toast("$statusCode(${statusCode.code}): ${message()}")
                    }
                }

                // map the ApiResponse.Failure.Error to a customized error model using the mapper.
                map(ErrorEnvelopeMapper) {
                    Log.d("GlobalResponseOperator.onError.map::","[Code: $code]: $message")
                }
            }
        }
    }

    // handles exceptional cases when the API request gets an exception response.
    // e.g., network connection error, timeout.
    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Log.d("GlobalResponseOperator.onException::",message())
                toast(message())
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}