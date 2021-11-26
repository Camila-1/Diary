package com.pchpsky.diary.screens.record.insulin.interfacies

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.InsulinsQuery

interface RecordInsulinRepository {
    suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data>
}