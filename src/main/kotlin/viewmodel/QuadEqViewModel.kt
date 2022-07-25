package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import entities.ComplexNumber
import services.IQuadraticEquationService

class QuadEqViewModel(private val quadEqService: IQuadraticEquationService) {

    val a: MutableState<Double?> = mutableStateOf(null)
    val b: MutableState<Double?> = mutableStateOf(null)
    val c: MutableState<Double?> = mutableStateOf(null)

    val result: MutableState<Pair<ComplexNumber, ComplexNumber>?> = mutableStateOf(null)

    var textOfEquation = ""
        get() = "${a.value ?: 0.0}*x^2 + ${b.value ?: 0.0}*x + ${c.value ?: 0.0} = 0"

    val errorMessage = mutableStateOf("")
    var isError = false
        get() = errorMessage.value.isNotEmpty()

    fun solveEquation() {
        if (a.value == null) {
            errorMessage.value = "Not a quadratic equation. 'a' shouldn't be zero."
            result.value = null
            return
        }

        errorMessage.value = ""
        result.value = quadEqService.solve(a.value!!, b.value ?: 0.0, c.value ?: 0.0)
    }

    fun strToDouble(text: String): Double? = try {
        text.toDouble()
    } catch (e: Exception) {
        null
    }
}