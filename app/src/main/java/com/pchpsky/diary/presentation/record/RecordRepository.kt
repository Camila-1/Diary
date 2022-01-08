package com.pchpsky.diary.presentation.record

import arrow.core.Either
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.schema.InsulinsQuery

class RecordRepository(private val networkClient: NetworkClient) : RecordInsulinRepository {

    override suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data> {
        return networkClient.insulins()
    }
}