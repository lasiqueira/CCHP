package main

import java.security.SecureRandom


class DShape(
    private val squares: FloatArray,
    private val rectangles: FloatArray,
    private val circles: FloatArray,
    private val triangles: FloatArray

    ) {
    private fun squareArea(i: Int) = squares[i] * squares[i]
    private fun rectangleArea(i: Int) = rectangles[i] * rectangles [i+1]
    private fun circleArea(i: Int) =  Math.PI * circles[i] * circles[i]
    private fun triangleArea(i: Int) = 0.5f * triangles[i] * triangles[i+1]

    fun totalArea() : Float
    {
        var totalAreaSquare = 0.0f
        var i = 0
        while(i < squares.size){
            totalAreaSquare+= squareArea(i)
            i++
        }
        i=0
        var totalAreaRectangle = 0.0f
        while(i < rectangles.size){
            totalAreaRectangle+= rectangleArea(i)
            i+=2
        }

        i=0
        var totalAreaCircle = 0.0f
        while(i < circles.size){
            totalAreaCircle+= circleArea(i).toFloat()
            i+=2
        }
        i=0
        var totalAreaTriangle = 0.0f
        while(i < triangles.size){
            totalAreaTriangle+= triangleArea(i)
            i+=2
        }
        return totalAreaSquare + totalAreaRectangle + totalAreaCircle + totalAreaTriangle
    }
}


fun buildDataOrientedShapes(count: Int): DShape {
    val secureRandom = SecureRandom()

    val squares = mutableListOf<Float>()
    val rectangles = mutableListOf<Float>()
    val triangles = mutableListOf<Float>()
    val circles = mutableListOf<Float>()
    for (i in 1..count) {
        when (secureRandom.nextInt(Int.MAX_VALUE) % 4) {
            0 -> squares.add(secureRandom.nextFloat())
            1 -> {
                rectangles.add(secureRandom.nextFloat())
                rectangles.add(secureRandom.nextFloat())
            }

            2 -> {
                triangles.add(secureRandom.nextFloat())
                triangles.add(secureRandom.nextFloat())
            }

            3 -> circles.add(secureRandom.nextFloat())
        }
    }
    return DShape(
        squares = squares.toFloatArray(),
        rectangles = rectangles.toFloatArray(),
        circles = circles.toFloatArray(),
        triangles = triangles.toFloatArray()
    )
}

fun dataOrientedTotalArea(shape: DShape) = shape.totalArea()