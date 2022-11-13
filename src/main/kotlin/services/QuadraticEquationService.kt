package services

import entities.ComplexNumber
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class QuadraticEquationService : IQuadraticEquationService {
    override suspend fun solve(a: Double, b: Double, c: Double): Pair<ComplexNumber, ComplexNumber> {
        val minus_b_div_2a = -b / (2*a)
        val d = b.pow(2) - 4*a*c
        val sqrtD_div_2a = sqrt(abs(d)) / (2*a)

        if (d < 0) {
            return Pair(
                ComplexNumber(minus_b_div_2a,  sqrtD_div_2a),
                ComplexNumber(minus_b_div_2a, -sqrtD_div_2a)
            )
        }

        return Pair(
            ComplexNumber(minus_b_div_2a + sqrtD_div_2a),
            ComplexNumber(minus_b_div_2a - sqrtD_div_2a)
        )
    }

    override fun test() = "Koin"
}