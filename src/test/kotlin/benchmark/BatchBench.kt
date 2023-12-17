package benchmark

import main.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class BatchBench {

    private val squares = mutableListOf<BSquare>()
    private val rectangles = mutableListOf<BRectangle>()
    private val triangles = mutableListOf<BTriangle>()
    private val circles = mutableListOf<BCircle>()
    private val count:Int = 1000
    @Setup
    fun setup(){
        buildBatchShapes(squares, rectangles, triangles, circles, count)
    }
    @Benchmark
    fun totalArea(){
        batchTotalArea(squares, rectangles, triangles, circles)
    }

}