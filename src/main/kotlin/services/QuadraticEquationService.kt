package services

import entities.ComplexNumber
import kotlin.math.pow
import kotlin.math.sqrt

class QuadraticEquationService : IQuadraticEquationService {
    override suspend fun solve(a: Double, b: Double, c: Double): Pair<ComplexNumber, ComplexNumber> {
        val d = b.pow(2) - 4*a*c

        if (d < 0) {
            val res1 = ComplexNumber(-b/(2*a), sqrt(-d) /(2*a))
            return Pair(res1, ComplexNumber(res1.re, -res1.im))
        }

        val sqrtD = sqrt(d)
        return Pair(
            ComplexNumber((-b + sqrtD) / (2*a)),
            ComplexNumber((-b - sqrtD) / (2*a))
        )
    }

    override fun test() = "Koin"
}