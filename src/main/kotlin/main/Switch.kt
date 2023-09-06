package main

import java.security.SecureRandom

enum class ShapeType{
    SQUARE,
    RECTANGLE,
    TRIANGLE,
    CIRCLE,
    COUNT,

}
inline fun <reified T : Enum<T>> Int.toEnum(): T? {
    return enumValues<T>().firstOrNull { it.ordinal == this }
}

inline fun <reified T : Enum<T>> T.toInt(): Int {
    return this.ordinal
}
data class ShapeUnion(val type: ShapeType, val width:Double, val height:Double)

fun buildSwitchShapes(count: Int): List<ShapeUnion>{
    val secureRandom = SecureRandom()
    val list = mutableListOf<ShapeUnion>()
    for (i in 1..count) {
        val shapeType = (secureRandom.nextInt(Int.MAX_VALUE)%4).toEnum<ShapeType>()

        shapeType?.let {
            val width = secureRandom.nextDouble()
            val height = if(it == ShapeType.CIRCLE) width else secureRandom.nextDouble()
            list.add(ShapeUnion(it, width, height))
        }
    }
    return list
}
fun switchArea(shape: ShapeUnion): Double{
    return when(shape.type){
        ShapeType.SQUARE -> shape.width * shape.width
        ShapeType.RECTANGLE -> shape.width * shape.height
        ShapeType.TRIANGLE -> 0.5 * shape.width * shape.height
        ShapeType.CIRCLE -> Math.PI * shape.width * shape.width
        ShapeType.COUNT -> 0.0
    }
}
fun switchTotalArea(list: List<ShapeUnion>) = list.sumOf { switchArea(it) }
