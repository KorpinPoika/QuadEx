package services

import entities.ComplexNumber

interface IQuadraticEquationService {
    suspend fun solve(a: Double, b: Double, c: Double): Pair<ComplexNumber, ComplexNumber>
    fun test(): String
}