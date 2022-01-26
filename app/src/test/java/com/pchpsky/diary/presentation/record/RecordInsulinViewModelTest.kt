package com.pchpsky.diary.presentation.record

import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.data.repositories.interfacies.InsulinDataSource
import com.pchpsky.diary.presentation.recordinsulin.InsulinViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class RecordInsulinViewModelTest {

    private val repository: InsulinDataSource = mock(InsulinDataSource::class.java)

    @Test
    fun selectInsulin_SetInsulin_StateWithSelectedInsulin() {
        val viewModel = InsulinViewModel(repository)

        val state = viewModel.uiState
        val selectedInsulin = Insulin("id", "color", "selected insulin name")
        viewModel.selectInsulin(selectedInsulin)

        assertEquals(selectedInsulin, state.value.selectedInsulin)
    }

    @Test
    fun incrementUnits_UnitsLessThenOneHundred_incrementUnit() {
        val viewModel = InsulinViewModel(repository)
        viewModel.incrementUnits()

        assertEquals(2.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun incrementUnits_UnitsEqualsOneHundred_UnitsNotIncremented() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("100.0")

        viewModel.incrementUnits()
        assertEquals(100.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassOne_UnitsEqualsOne_UnitsSet() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("1.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassMoreThenOneHundred_UnitsNotSet() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.setUnits("101.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassLessThenNull_UnitsNotSet() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.setUnits("-1.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun decrementUnits_UnitsEqualsOne_UnitsNotDecremented() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.decrementUnits()

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun decrementUnits_UnitsMoreThenOne_UnitsDecremented() {
        val viewModel = InsulinViewModel(repository)
        viewModel.setUnits("5.0")
        viewModel.decrementUnits()

        assertEquals(4.0, viewModel.uiState.value.units, 0.0)
    }
}