package services

import entities.ComplexNumber

interface IQuadraticEquationService {
    fun solve(a: Double, b: Double, c: Double): Pair<ComplexNumber, ComplexNumber>
    fun test(): String
}