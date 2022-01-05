package com.pchpsky.diary.screens.record

import arrow.core.Either
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.schema.InsulinsQuery
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class RecordViewModelTest {

    private val repository: RecordInsulinRepository = mock(RecordInsulinRepository::class.java)

    @Test
    fun selectInsulin_SetInsulin_StateWithSelectedInsulin() {
        val viewModel = RecordViewModel(repository)

        val state = viewModel.uiState
        val selectedInsulin = Insulin("id", "color", "selected insulin name")
        viewModel.selectInsulin(selectedInsulin)

        assertEquals(selectedInsulin, state.value.selectedInsulin)
    }

    @Test
    fun incrementUnits_UnitsLessThenOneHundred_incrementUnit() {
        val viewModel = RecordViewModel(repository)
        viewModel.incrementUnits()

        assertEquals(2.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun incrementUnits_UnitsEqualsOneHundred_UnitsNotIncremented() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("100.0")

        viewModel.incrementUnits()
        assertEquals(100.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassOne_UnitsEqualsOne_UnitsSet() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("1.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassMoreThenOneHundred_UnitsNotSet() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.setUnits("101.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun setUnits_PassLessThenNull_UnitsNotSet() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.setUnits("-1.0")

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun decrementUnits_UnitsEqualsOne_UnitsNotDecremented() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("1.0")
        viewModel.decrementUnits()

        assertEquals(1.0, viewModel.uiState.value.units, 0.0)
    }

    @Test
    fun decrementUnits_UnitsMoreThenOne_UnitsDecremented() {
        val viewModel = RecordViewModel(repository)
        viewModel.setUnits("5.0")
        viewModel.decrementUnits()

        assertEquals(4.0, viewModel.uiState.value.units, 0.0)
    }
}