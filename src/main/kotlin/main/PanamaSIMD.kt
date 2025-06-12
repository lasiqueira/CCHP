@file:JvmName("PanamaSIMD")
package main

import jdk.incubator.vector.DoubleVector
import jdk.incubator.vector.VectorOperators
import jdk.incubator.vector.VectorSpecies

class D64Array(private val data: DoubleArray) {
    fun dot(other: D64Array): Double = dotSIMD(data, other.data)
    fun sum(): Double = sumSIMD(data)
    val size get() = data.size

    operator fun get(index: Int) = data[index]
    operator fun iterator(): DoubleIterator = data.iterator()

    companion object {
        fun of(vararg values: Double) = D64Array(values)
    }
}

private val SPECIES: VectorSpecies<Double> = DoubleVector.SPECIES_PREFERRED

private fun dotSIMD(a: DoubleArray, b: DoubleArray): Double {
    var i = 0
    var acc = DoubleVector.zero(SPECIES)
    val upperBound = SPECIES.loopBound(a.size.coerceAtMost(b.size))

    while (i < upperBound) {
        val va = DoubleVector.fromArray(SPECIES, a, i)
        val vb = DoubleVector.fromArray(SPECIES, b, i)
        acc = va.fma(vb, acc)
        i += SPECIES.length()
    }

    var result = acc.reduceLanes(VectorOperators.ADD)
    while (i < a.size && i < b.size) {
        result += a[i] * b[i]
        i++
    }

    return result
}

private fun sumSIMD(a: DoubleArray): Double {
    var i = 0
    var acc = DoubleVector.zero(SPECIES)
    val upperBound = SPECIES.loopBound(a.size)

    while (i < upperBound) {
        val va = DoubleVector.fromArray(SPECIES, a, i)
        acc = acc.add(va)
        i += SPECIES.length()
    }

    var result = acc.reduceLanes(VectorOperators.ADD)
    while (i < a.size) {
        result += a[i]
        i++
    }

    return result
}
