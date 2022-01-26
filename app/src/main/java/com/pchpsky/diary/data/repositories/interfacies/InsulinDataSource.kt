package com.pchpsky.diary.data.repositories.interfacies

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.InsulinsQuery

interface InsulinDataSource {
    suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data>
}