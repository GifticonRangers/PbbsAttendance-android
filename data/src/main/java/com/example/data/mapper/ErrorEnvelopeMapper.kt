package com.example.data.mapper

import com.example.data.model.ErrorEnvelope
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorEnvelopeMapper : ApiErrorModelMapper<ErrorEnvelope> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorEnvelope {
        return ErrorEnvelope(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}