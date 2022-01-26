package com.pchpsky.diary.data.repositories

import arrow.core.Either
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.interfacies.InsulinDataSource
import com.pchpsky.schema.InsulinsQuery

class RecordInsulinRepository(private val networkClient: NetworkClient) :
    InsulinDataSource {

    override suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data> {
        return networkClient.insulins()
    }
}