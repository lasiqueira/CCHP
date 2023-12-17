package main

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main() {
    //setup
    val count = 10000000
    val squares = mutableListOf<BSquare>()
    val rectangles = mutableListOf<BRectangle>()
    val triangles = mutableListOf<BTriangle>()
    val circles = mutableListOf<BCircle>()
    buildBatchShapes(squares, rectangles, triangles, circles, count)
    val cleanShapes = buildCleanShapes(count)
    val switchShapes = buildSwitchShapes(count)
    val shape = buildDataOrientedShapes(count)
    val tableShapes = buildTableShapes(count)

    //measurements

    //clean
    val clean = measureTimeMillis {
        cleanTotalArea(cleanShapes)
    }
    println("Clean: $clean ms")
    //switch
    val switch = measureTimeMillis {
        switchTotalArea(switchShapes)
    }
    println("Switch $switch ms")
    //table
    val table = measureTimeMillis {
        tableTotalArea(tableShapes)
    }
    println("Table: $table ms")

    //batch
    val batch = measureTimeMillis {
        batchTotalArea(squares, rectangles, triangles, circles)
    }
    println("Batch: $batch ms")
    //data oriented
    val dataOriented = measureTimeMillis {
        dataOrientedTotalArea(shape)
    }
    println("DataOriented: $dataOriented ms")

}