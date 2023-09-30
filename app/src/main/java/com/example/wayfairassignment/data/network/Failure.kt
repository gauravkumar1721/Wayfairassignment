package com.example.wayfairassignment.data.network

import com.example.wayfairassignment.domain.common.ErrorModel
import com.example.wayfairassignment.domain.common.IFailure

sealed class Failure(override val errorModel: ErrorModel) : IFailure {
    class HttpError(error: ErrorModel) : Failure(error)
    class DisplayableError(error: ErrorModel) : Failure(error)
}
