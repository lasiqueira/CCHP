package benchmark

import main.Shape
import main.buildCleanShapes
import main.cleanTotalArea
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class CleanBench {

    private lateinit var list: List<Shape>
    private val count:Int = 1000
    @Setup
    fun setup(){
        list = buildCleanShapes(count)
    }
    @Benchmark
    fun totalArea(){
        cleanTotalArea(list)
    }

}