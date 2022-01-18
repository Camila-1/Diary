package com.pchpsky.diary.presentation.record.insulin.interfacies

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.InsulinsQuery

interface RecordInsulinRepository {
    suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data>
}