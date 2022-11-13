import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import services.IQuadraticEquationService
import services.QuadraticEquationService

@DisplayName("Tests of Quadratic Equation Service")
class QuadraticEquationServiceTests {

    private val qadExService: IQuadraticEquationService by lazy { QuadraticEquationService()  }

    @DisplayName("Real roots test")
    @Test
    fun realRootsTest() = runBlocking {
        val res = qadExService.solve(1.0, 0.0, -9.0)

        assertEquals( 3.0, res.first.re, "x1 real")
        assertEquals( 0.0, res.first.im, "x1 imaginary")

        assertEquals( -3.0, res.second.re, "x2 real")
        assertEquals( 0.0, res.second.im, "x2 imaginary")
    }

    @DisplayName("Imaginary roots test")
    @Test
    fun imaginaryRootsTest() = runBlocking {
        val res = qadExService.solve(2.0, 6.0, 9.0)

        assertEquals(-1.5, res.first.re, "x1 real")
        assertEquals( 1.5, res.first.im, "x1 imaginary")

        assertEquals(-1.5, res.second.re, "x2 real")
        assertEquals(-1.5, res.second.im, "x2 imaginary")
    }

    @DisplayName("One root test")
    @Test
    fun oneRootTest() = runBlocking {
        val res = qadExService.solve(1.0, 6.0, 9.0)

        assertEquals(-3.0, res.first.re, "x1 real")
        assertEquals( 0.0, res.first.im, "x1 imaginary")

        assertEquals(-3.0, res.second.re, "x2 real")
        assertEquals( 0.0, res.second.im, "x2 imaginary")
    }
}