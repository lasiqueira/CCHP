package main

import java.security.SecureRandom

class DSquare(private val side:Double){
    fun area() = this.side * side
}
class DRectangle(private val width:Double, private val height: Double){
    fun area() = width * height
}
class DTriangle(private val base:Double, private val height:Double){
    fun area() = 0.5 * base * height
}
class DCircle(private val radius:Double){
    fun area() = Math.PI * radius * radius
}


fun buildDataOrientedShapes(squares:MutableList<DSquare>,rectangles:MutableList<DRectangle>,triangles :MutableList<DTriangle>, circles: MutableList<DCircle>, count: Int){
    val secureRandom = SecureRandom()

    for (i in 0 .. count) {
        when(secureRandom.nextInt()%4){
            0 -> squares.add(DSquare(secureRandom.nextDouble()))
            1-> rectangles.add(DRectangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
            2 -> triangles.add(DTriangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
            3 -> circles.add(DCircle(secureRandom.nextDouble()))
        }
    }
}
fun dataOrientedTotalArea(squares:MutableList<DSquare>,rectangles:MutableList<DRectangle>,triangles :MutableList<DTriangle>, circles: MutableList<DCircle>)=
    squares.sumOf { it.area() } + rectangles.sumOf { it.area() } + triangles.sumOf { it.area() } + circles.sumOf { it.area() }
