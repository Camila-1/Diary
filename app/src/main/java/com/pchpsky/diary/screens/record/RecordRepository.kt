package com.pchpsky.diary.screens.record

import arrow.core.Either
import com.pchpsky.diary.datasource.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.schema.InsulinsQuery

class RecordRepository(private val networkClient: NetworkClient) : RecordInsulinRepository {

    override suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data> {
        return networkClient.insulins()
    }
}