package main

import java.security.SecureRandom

//Clean
open class Shape{
    open fun area() = 0.0
}
class Square(private val side:Double): Shape(){

    override fun area() = this.side * side
}
class Rectangle(private val width:Double, private val height: Double): Shape(){
    override fun area() = width * height
}
class Triangle(private val base:Double, private val height:Double): Shape(){
    override fun area() = 0.5 * base * height
}
class Circle(private val radius:Double): Shape() {
    override fun area() = Math.PI * radius * radius
}
fun buildCleanShapes(count: Int): List<Shape>{
    val secureRandom = SecureRandom()
    val list = mutableListOf<Shape>()
    for (i in 1 ..count) {
       when(secureRandom.nextInt(Int.MAX_VALUE)%4){
           0 -> list.add(Square(secureRandom.nextDouble()))
           1-> list.add(Rectangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
           2 -> list.add(Triangle(secureRandom.nextDouble(), secureRandom.nextDouble()))
           3 -> list.add(Circle(secureRandom.nextDouble()))
       }
    }
    return list
}
fun cleanTotalArea(list:List<Shape>)= list.sumOf { it.area() }

fun cleanTotalAreaPattern(list:List<Shape>)= list.sumOf {
    when(it){
        is Square ->  it.area()
        is Circle ->  it.area()
        is Rectangle ->  it.area()
        is Triangle ->  it.area()
        else -> 0.0
    }
}

