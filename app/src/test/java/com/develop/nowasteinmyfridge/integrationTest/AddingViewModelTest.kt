package com.develop.nowasteinmyfridge.integrationTest

import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.develop.nowasteinmyfridge.domain.AddIngredientUseCase
import com.develop.nowasteinmyfridge.domain.GetIngredientByBarcodeUseCase
import com.develop.nowasteinmyfridge.feature.adding.AddingViewModel
import com.develop.nowasteinmyfridge.util.Result
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AddingViewModelTest {

    private lateinit var addingViewModel: AddingViewModel

    @Before
    fun setup() {
        val addIngredientUseCase = mockk<AddIngredientUseCase>()
        val getIngredientByBarcodeUseCase = mockk<GetIngredientByBarcodeUseCase>()
        addingViewModel = AddingViewModel(addIngredientUseCase, getIngredientByBarcodeUseCase)
    }

    @Test
    fun `test addIngredient success`() = runTest {
        val ingredient = IngredientCreate(image = "")

        coEvery { addingViewModel.addIngredientUseCase.invoke(ingredient) } just Runs

        addingViewModel.addIngredient(ingredient)
        // Await until the coroutine completes
        advanceUntilIdle()

        assertThat(addingViewModel.addIngredientResult.value, `is`(Result.Success(Unit)))
    }
}
