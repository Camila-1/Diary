package com.pchpsky.diary.screens.record

import com.pchpsky.diary.datasource.network.NetworkClient
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinRepository

class RecordRepository(private val networkClient: NetworkClient) : RecordInsulinRepository {
}