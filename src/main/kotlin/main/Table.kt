package main

val table = listOf(1.0, 1.0, 0.5, Math.PI)

fun tableArea(shape: ShapeUnion) = table[shape.type.toInt()] * shape.width * shape.height

fun tableTotalArea(list:List<ShapeUnion>) = list.sumOf { tableArea(it) }

fun buildTableShapes(count: Int) = buildSwitchShapes(count)