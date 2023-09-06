package benchmark

import main.ShapeUnion
import main.buildSwitchShapes
import main.switchTotalArea
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class SwitchBench {
    private lateinit var list: List<ShapeUnion>
    private val count:Int = 1000
    @Setup
    fun setup(){
        list = buildSwitchShapes(count)
    }
    @Benchmark
    fun totalArea(){
        switchTotalArea(list)
    }
}