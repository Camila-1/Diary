package com.pchpsky.diary.screens.settings

import arrow.core.Right
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.interfaces.SettingsRepository
import com.pchpsky.schema.CreateInsulinMutation
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class SettingsViewModelTest {

    val settingsRepository = mock(SettingsRepository::class.java)

    @Test
    fun addInsulin_PassNewInsulin_insulinAddedToList() = runBlocking {
        val viewModel = SettingsViewModel(settingsRepository)
        val insulin = mock(CreateInsulinMutation.Insulin::class.java)

        `when`(settingsRepository.createInsulin("", "")).thenReturn(Right(CreateInsulinMutation.Data(insulin)))
        val newInsulin = Insulin("id", "color", "insulin name")

        viewModel.addInsulin("color", "insulin name")

        assertTrue(viewModel.uiState.value.insulins.contains(newInsulin))
    }
}