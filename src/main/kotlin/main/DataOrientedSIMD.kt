package main

import java.security.SecureRandom

class DShapeSIMD(
    private val squares: D64Array,
    private val rectangles1: D64Array,
    private val rectangles2: D64Array,
    private val circles1: D64Array,
    private val circles2: D64Array,
    private val triangles1: D64Array,
    private val triangles2: D64Array
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
        return D64Array.of(totalAreaTriangle, totalAreaCircle, totalAreaRectangle, totalAreaSquare).sum()
    }

    fun totalAreaMT() : Double
    {
        var totalAreaSquare: Double = 0.0
        val squareThread = Thread {
            totalAreaSquare = squareArea()
        }
        squareThread.start()
        var totalAreaRectangle: Double = 0.0
        val rectangleThread = Thread {
            totalAreaRectangle = rectangleArea()
        }
        rectangleThread.start()
        var totalAreaCircle: Double = 0.0
        val circleThread = Thread {
            totalAreaCircle = circleArea()
        }
        circleThread.start()
        var totalAreaTriangle: Double = 0.0
        val triangleThread = Thread {
            totalAreaTriangle = triangleArea()
        }
        triangleThread.start()
        squareThread.join()
        rectangleThread.join()
        circleThread.join()
        triangleThread.join()

        return D64Array.of(totalAreaTriangle, totalAreaCircle, totalAreaRectangle, totalAreaSquare).sum()
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
        squares = D64Array(squares.toDoubleArray()),
        rectangles1 = D64Array(rectangles1.toDoubleArray()),
        rectangles2 = D64Array(rectangles2.toDoubleArray()),
        circles1 = D64Array(circles1.toDoubleArray()),
        circles2 = D64Array(circles1.toDoubleArray()),
        triangles1 = D64Array(triangles1.toDoubleArray()),
        triangles2 = D64Array(triangles2.toDoubleArray())
    )
}

fun dataOrientedSIMDTotalArea(shape: DShapeSIMD) = shape.totalArea()
fun dataOrientedSIMDTotalAreaMT(shape: DShapeSIMD) = shape.totalAreaMT()