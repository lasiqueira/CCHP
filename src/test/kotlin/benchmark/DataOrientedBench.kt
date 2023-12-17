package benchmark

import main.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class DataOrientedBench {
    private lateinit var shape: DShape
    private val count:Int = 1000
    @Setup
    fun setup(){
       shape = buildDataOrientedShapes(count)
    }
    @Benchmark
    fun totalArea(){
        dataOrientedTotalArea(shape)
    }
}