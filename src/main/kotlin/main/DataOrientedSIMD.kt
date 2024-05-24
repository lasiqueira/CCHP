package main

import org.jetbrains.bio.viktor.F64Array
import org.jetbrains.bio.viktor.asF64Array
import java.security.SecureRandom


class DShapeSIMD(
    private val squares: F64Array,
    private val rectangles1: F64Array,
    private val rectangles2: F64Array,
    private val circles1: F64Array,
    private val circles2: F64Array,
    private val triangles1: F64Array,
    private val triangles2: F64Array
    ) {
    private fun squareArea() = squares.dot(squares)
    private fun rectangleArea() = rectangles1.dot(rectangles2)
    private fun circleArea() =  circles1.dot(circles2)
    private fun triangleArea() = triangles1.dot(triangles2)

    fun totalArea() : Double
    {
        val totalAreaSquare = squareArea()
        val totalAreaRectangle = rectangleArea()
        val totalAreaCircle = circleArea()
        val totalAreaTriangle = triangleArea()
        return F64Array.of(totalAreaTriangle, totalAreaCircle, totalAreaRectangle, totalAreaSquare).sum()
    }
}


fun buildDataOrientedSIMDShapes(count: Int): DShapeSIMD {
    val secureRandom = SecureRandom()

    val squares = mutableListOf<Double>()
    val rectangles1 = mutableListOf<Double>()
    val rectangles2 = mutableListOf<Double>()
    val triangles1 = mutableListOf<Double>()
    val triangles2 = mutableListOf<Double>()
    val circles1 = mutableListOf<Double>()
    val circles2 = mutableListOf<Double>()
    for (i in 1..count) {
        when (secureRandom.nextInt(Int.MAX_VALUE) % 4) {
            0 -> squares.add(secureRandom.nextDouble())
            1 -> {
                rectangles1.add(secureRandom.nextDouble())
                rectangles2.add(secureRandom.nextDouble())
            }

            2 -> {
                triangles1.add(secureRandom.nextDouble())
                triangles2.add(secureRandom.nextDouble()* 0.5)
            }

            3 -> {
                val d = secureRandom.nextDouble()
                circles1.add(d)
                circles2.add(d * Math.PI)
                }
        }
    }
    return DShapeSIMD(
        squares = squares.toDoubleArray().asF64Array(),
        rectangles1 = rectangles1.toDoubleArray().asF64Array(),
        rectangles2 = rectangles2.toDoubleArray().asF64Array(),
        circles1 = circles1.toDoubleArray().asF64Array(),
        circles2 = circles1.toDoubleArray().asF64Array(),
        triangles1 = triangles1.toDoubleArray().asF64Array(),
        triangles2 = triangles2.toDoubleArray().asF64Array()
    )
}

fun dataOrientedSIMDTotalArea(shape: DShapeSIMD) = shape.totalArea()