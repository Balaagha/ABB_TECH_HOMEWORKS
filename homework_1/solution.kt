
import java.lang.IllegalArgumentException

fun main() {
    val listInts = listOf(1, 3, 5, 4, 2, 5)
    val listStrs = listOf('a', 'B', 'A', 'b', 'A', 'a')
    println(listInts.uniqueOrdered())
    println(listStrs.uniqueOrdered())
}

@Suppress("UNCHECKED_CAST")
fun <T> List<T>.uniqueOrdered(): List<T> {
    return when (this.firstOrNull()) {
        is Char -> {
            (this as List<Char>).distinct().sorted() as List<T>
        }
        is Int -> {
            (this as List<Int>).distinct().sorted() as List<T>
        }
        else -> {
            throw IllegalArgumentException("List type must be int or char ")
        }
    }
}