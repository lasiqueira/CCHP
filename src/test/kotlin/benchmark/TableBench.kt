package benchmark

import main.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork
open class TableBench {
    private lateinit var list: List<ShapeUnion>
    private val count:Int = 1000
    @Setup
    fun setup(){
        list = buildTableShapes(count)
    }
    @Benchmark
    fun totalArea(){
        tableTotalArea(list)
    }
}