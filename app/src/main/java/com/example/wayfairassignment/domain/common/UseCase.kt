package com.example.wayfairassignment.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.Result

abstract class UseCase<in Params, out Type> where Type : Any? {
    abstract suspend fun run(params: Params): com.example.wayfairassignment.domain.common.Result<IFailure, Type>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (IFailure) -> Unit = {}
    ) {
        val job = scope.async { run(params) }
        scope.launch {
            job.await().result(onFailure, onSuccess)
        }
    }
}