package kotlinCourse2022.section_8_chanel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.atomic.AtomicReference

fun main() = runBlocking {
    getNumbers().withLatestFrom(getString()) { number, string -> "$number$string " }.collect { value ->
        print(value)
    }
    println(".")
    getNumbers().combineLatest(getString()) { number, string -> "$number$string " }.collect { value ->
        print(value)
    }
}

private fun getNumbers(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(
            when (i) {
                1 -> 100
                2 -> 300
                3 -> 800
                4 -> 100
                5 -> 100
                else -> 100
            }
        )
        emit(i)
    }
}
private fun getString(): Flow<String> = flow {
    listOf<String>("A", "B", "C", "D").forEach {
        delay(
            when (it) {
                "A" -> 300
                "B" -> 200
                "C" -> 100
                "D" -> 100
                else -> 100
            }
        )
        emit(it)
    }
}


fun <A, B, R> Flow<A>.combineLatest(other: Flow<B>, transform: suspend (A, B) -> R): Flow<R> =
    channelFlow {
        coroutineScope {
            val outerScope = this

            val latestA = AtomicReference<A?>(null)
            val latestB = AtomicReference<B?>(null)
            launch {
                try {
                    collect { a: A ->
                        latestA.set(a)
                        latestB.get()?.let { b ->
                            send(transform(a, b))
                        }
                    }
                } catch (e: CancellationException) {
                    outerScope.cancel(e)
                }
            }
            launch {
                try {
                    other.collect { b: B ->
                        latestB.set(b)
                        latestA.get()?.let { a ->
                            send(transform(a, b))
                        }
                    }
                } catch (e: CancellationException) {
                    outerScope.cancel(e)
                }
            }
        }
    }

// referanse: https://github.com/Kotlin/kotlinx.coroutines/issues/1498
fun <A, B, R> Flow<A>.withLatestFrom(other: Flow<B>, transform: suspend (A, B) -> R): Flow<R> =
    flow {
        coroutineScope {
            val latestB = AtomicReference<B?>(null)
            val outerScope = this

            launch {
                try {
                    other.collect { latestB.set(it) }
                } catch (e: CancellationException) {
                    outerScope.cancel(e) // cancel outer scope on cancellation exception, too
                }
            }

            collect { a: A ->
                latestB.get()?.let {
                    this@flow.emit(transform(a, it))
                }
            }
        }
    }


