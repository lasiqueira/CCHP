package benchmark

import main.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class DataOrientedSIMDBench {
    private lateinit var shape: DShapeSIMD
    private val count:Int = 1000
    @Setup
    fun setup(){
       shape = buildDataOrientedSIMDShapes(count)
    }
    @Benchmark
    fun totalArea(){
        dataOrientedSIMDTotalArea(shape)
    }
}