package com.pchpsky.diary.presentation.settings

import arrow.core.Right
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.settings.interfaces.SettingsRepository
import com.pchpsky.schema.CreateInsulinMutation
import com.pchpsky.schema.DeleteInsulinMutation
import com.pchpsky.schema.SettingsQuery
import com.pchpsky.schema.UpdateSettingsMutation
import com.pchpsky.schema.type.BloodGlucoseUnits
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*


class SettingsViewModelTest {

    private val settingsRepository = mock(SettingsRepository::class.java)
    val vm = UserSettingsViewModel(settingsRepository)

    @Test
    fun addInsulin_PassNewInsulin_insulinAddedToList() = runBlocking {
        val viewModel = UserSettingsViewModel(settingsRepository)
        val createdInsulin = CreateInsulinMutation.Insulin(
            "Insulin",
            "id",
            "color",
            "insulin name"
        )
        val insulinToBeContained = Insulin("id", "color", "insulin name")

        `when`(settingsRepository.createInsulin(anyString(), anyString()))
            .thenReturn(Right(CreateInsulinMutation.Data(createdInsulin)))
        viewModel.addInsulin("color", "insulin name")

        assertTrue(viewModel.uiState.value.insulins.contains(insulinToBeContained))
    }

    @Test
    fun updateGlucoseUnit_PassUnit_UnitUpdated() = runBlocking {
        val viewModel = UserSettingsViewModel(settingsRepository)
        val settingsMutation = UpdateSettingsMutation.Settings("Settings", BloodGlucoseUnits.MMOL_PER_L)
        `when`(settingsRepository.updateGlucoseUnit(GlucoseUnits.MMOL_PER_L.name))
            .thenReturn(Right(UpdateSettingsMutation.Data(settingsMutation)))

        viewModel.updateGlucoseUnit(GlucoseUnits.MMOL_PER_L)

        assertEquals(GlucoseUnits.MMOL_PER_L.unit, viewModel.uiState.value.glucoseUnit)
    }

    @Test
    fun settings_RequestSettingsReturnsRight_SettingsUpdated() = runBlocking {
        val viewModel = UserSettingsViewModel(settingsRepository)
        val settings = SettingsQuery.Settings(
            "Settings",
            BloodGlucoseUnits.MG_PER_DL,
            listOf(SettingsQuery.Insulin("Insulin", "id", "name", "color"))
        )
        `when`(settingsRepository.settings()).thenReturn(Right(SettingsQuery.Data(settings)))

        viewModel.settings()

        assertEquals(viewModel.uiState.value.glucoseUnit, GlucoseUnits.MG_PER_DL.unit)
        assertEquals(viewModel.uiState.value.insulins, listOf(Insulin("id", "color", "name")))
    }

    @Test
    fun deleteInsulin_PassInsulinId_InsulinDeletedById() = runBlocking {
        val viewModel = UserSettingsViewModel(settingsRepository)
        val createdInsulin = CreateInsulinMutation.Insulin(
            "Insulin",
            "id",
            "color",
            "insulin name"
        )
        val deletedInsulin = DeleteInsulinMutation.Insulin(
            "Insulin",
            "id",
            "color",
            "insulin name"
        )

        `when`(settingsRepository.createInsulin(anyString(), anyString()))
            .thenReturn(Right(CreateInsulinMutation.Data(createdInsulin)))
        viewModel.addInsulin("color", "insulin name")
        `when`(settingsRepository.deleteInsulin(anyString()))
            .thenReturn(Right(DeleteInsulinMutation.Data(deletedInsulin)))
        viewModel.deleteInsulin("id")

        assertTrue(viewModel.uiState.value.insulins.isEmpty())
    }
}