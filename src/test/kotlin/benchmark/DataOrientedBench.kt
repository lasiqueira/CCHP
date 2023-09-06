package benchmark

import main.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class DataOrientedBench {

    private val squares =  mutableListOf<DSquare>()
    private val rectangles =  mutableListOf<DRectangle>()
    private val triangles =  mutableListOf<DTriangle>()
    private val circles =  mutableListOf<DCircle>()
    private val count:Int = 1000
    @Setup
    fun setup(){
        buildDataOrientedShapes(squares, rectangles, triangles, circles, count)
    }
    @Benchmark
    fun totalArea(){
        dataOrientedTotalArea(squares, rectangles, triangles, circles)
    }

}