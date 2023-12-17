package main
import java.security.SecureRandom
import kotlin.concurrent.thread

class BSquare(private val side:Double){
    fun area() = this.side * side
}
class BRectangle(private val width:Double, private val height: Double){
    fun area() = width * height
}
class BTriangle(private val base:Double, private val height:Double){
    fun area() = 0.5 * base * height
}
class BCircle(private val radius:Double){
    fun area() = Math.PI * radius * radius
}


fun buildBatchShapes(squares:MutableList<BSquare>, rectangles:MutableList<BRectangle>, triangles :MutableList<BTriangle>, circles: MutableList<BCircle>, count: Int){
    val secureRandom = SecureRandom()

    for (i in 1 .. count) {
        when(secureRandom.nextInt(Int.MAX_VALUE)%4){
            0 -> squares.add(BSquare(secureRandom.nextDouble()))
            1-> rectangles.add(BRectangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
            2 -> triangles.add(BTriangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
            3 -> circles.add(BCircle(secureRandom.nextDouble()))
        }
    }
}
fun batchTotalArea(squares:MutableList<BSquare>, rectangles:MutableList<BRectangle>, triangles :MutableList<BTriangle>, circles: MutableList<BCircle>)=
    squares.sumOf { it.area() } + rectangles.sumOf { it.area() } + triangles.sumOf { it.area() } + circles.sumOf { it.area() }
