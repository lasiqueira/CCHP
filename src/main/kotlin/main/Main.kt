package main

import kotlin.system.measureTimeMillis

fun main() {
    //setup
    val count = 500000
    val squares = mutableListOf<BSquare>()
    val rectangles = mutableListOf<BRectangle>()
    val triangles = mutableListOf<BTriangle>()
    val circles = mutableListOf<BCircle>()
    buildBatchShapes(squares, rectangles, triangles, circles, count)
    val cleanShapes = buildCleanShapes(count)
    val switchShapes = buildSwitchShapes(count)
    val shape = buildDataOrientedShapes(count)
    val shapeSIMD = buildDataOrientedSIMDShapes(count)
    val tableShapes = buildTableShapes(count)
    //warmup
    for(i in 1..10){
        cleanTotalArea(cleanShapes)
        cleanTotalAreaPattern(cleanShapes)
        switchTotalArea(switchShapes)
        tableTotalArea(tableShapes)
        batchTotalArea(squares, rectangles, triangles, circles)
        dataOrientedTotalArea(shape)
        dataOrientedTotalAreaMT(shape)
        dataOrientedSIMDTotalArea(shapeSIMD)
        dataOrientedSIMDTotalAreaMT(shapeSIMD)

    }
    //measurements
    println("Shape count = $count")
    //clean
    val clean = measureTimeMillis {
        cleanTotalArea(cleanShapes)
    }
    println("Clean: $clean ms")
    //clean pat
    val cleanPat = measureTimeMillis {
        cleanTotalAreaPattern(cleanShapes)
    }
    println("Clean Pattern matching: $cleanPat ms")
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

    val dataOrientedMT = measureTimeMillis {
        dataOrientedTotalAreaMT(shape)
    }
    println("DataOrientedMT: $dataOrientedMT ms")

    val dataOrientedSIMD = measureTimeMillis {
        dataOrientedSIMDTotalArea(shapeSIMD)
    }
    println("DataOrientedSIMD: $dataOrientedSIMD ms")

    val dataOrientedSIMDMT = measureTimeMillis {
        dataOrientedSIMDTotalAreaMT(shapeSIMD)
    }
    println("DataOrientedSIMDMT: $dataOrientedSIMDMT ms")

}