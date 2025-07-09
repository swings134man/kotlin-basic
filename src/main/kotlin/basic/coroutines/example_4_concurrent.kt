package com.lucas.basic.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * example_4_concurrent.kt: 코루틴에서 동시에 처리하는 문제에 관한 내용
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 9. 오후 3:36
 * @description: 메서드 one(), two() 는 각각 1초씩 걸리는 작업을 수행함.
 * - main thread 에서 실행한다면 1초 + 1초 = 2초 -> 코루틴을 사용한 병렬처리가 수행되지 않는다 ->
 * - async/await 를 이용하여 병렬처리를 수행하고, 결과가 도출될때 까지 대기 하도록 한다
 *
 * - suspending 함수를 사용해서, 순차적, 병렬적으로 작업을 수행할 수 있다.
 * - LAZY 를 활용하여, 코루틴을 바로 실행하지 않고, 필요할때 실행하도록 할 수 있다.(특정 시점에 call)
 */
fun main() {
//    sync()
//    async()
//    runConcurrentSum()
    runFail()
}


// 이렇게 실행하였을 경우, one() 와 two() 는 `순차적`으로 실행되며, 총 2초가 소요된다. -> 병행성의 장점을 활용하지 못함
private fun sync() = runBlocking {
    val time = measureTimeMillis {
        val oneResult = one() // 1초
        val twoResult = two() // 1초
        println("Total RESULT : ${oneResult + twoResult}")
    }
    println("Competed in $time ms") // 2000ms^
}

// async/await 를 사용하여 병행처리를 수행함.
// - async 는 코루틴 빌더로, 비동기적으로 작업을 수행하고, await() 를 사용하여 결과를 기다린다.
// 병행처리를 하려면 `명시적`으로 처리해야 한다 (async/await 사용)
private fun async() = runBlocking {
    val time = measureTimeMillis {
        // async 를 사용하여 병렬처리
        val oneResult = async { one() }
        val twoResult = async { two() }

        // await() 를 사용하여 결과를 기다림
        println("Total RESULT : ${oneResult.await() + twoResult.await()}")
    }
    println("Competed in $time ms") // 1000ms^
}

// ----------------------------------extract--------------------------------------

/**
 * - 함수 자체를 Async Function 으로 만들어서, 어디서든 호출할때 코루틴 스코프 내에서 실행될 수 있도록 하고싶을 수 있다.
 * - 이 경우에는 함수를 GlobalScope 를 사용하여 launch 하게 하는것은 좋지 않다. -> 구조적 동시성(Structured Concurrency) 위반
 *      -> Exception 발생시, 호출된곳에서 중단되지 않고, 코루틴은 계속 실행하게 되므로, 프로그램의 안정성이 떨어지게 된다.
 * - 따라서 코루틴 Scope 내에서 실행될 수 있도록 구조를 만드는것이 중요하다.
 *
 * - 코루틴 내부에서만 호출할 수 있는 function
 */
private suspend fun concurrentSum() : Int = coroutineScope {
    val oneRes = async{one()}
    val twoRes = async{two()}

    // Exception 발생시, 호출한곳에서 더이상 코루틴을 실행하지 않고 종료됨 -> 예외처리/전파
//    delay(10L)
//    println("Exception !!")
//    throw Exception("Error")

    oneRes.await() + twoRes.await()
}

// 단순히 concurrentSum() 함수 호출
private fun runConcurrentSum() = runBlocking {
    val time = measureTimeMillis {
        val result = concurrentSum()
        println("Total RESULT : $result")
    }
    println("Competed in $time ms") // 1000ms^
}


// ---------------------------------- Fail --------------------------------------
// Exception Flow: twoRes 내부 -> oneRes finally -> runFail Catch -> runFail Finally
private fun runFail() = runBlocking {
    try {
        failConcurrentSum()
    }catch (e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }finally {
        println("Computation cancelled")
    }
}

/**
 * one(), two() 가 동시에 실행되지만, two() 에서 Exception 이 발생한다면, one() 에서는 작업을 취소하여 finally 블록이 실행되고.
 * 호출한곳 `runFail()` 에서도 catch 하게 된다.
 */
suspend fun failConcurrentSum() : Int = coroutineScope {
    val oneRes = async {
        try {
            delay(Long.MAX_VALUE)
            42
        }finally {
            println("oneRes cancelled by EXCEPTION")
        }
    }

    val twoRes = async {
        println("twoRes EXCEPTION!")
        throw ArithmeticException()
        10
    }

    oneRes.await() + twoRes.await()
}



// ----------------------------------Common--------------------------------------
private suspend fun one(): Int {
    delay(1000L) // 이 작업은 CPU 연산, API 호출과 같은 작업을 의미한다.
    return 10
}

private suspend fun two(): Int {
    delay(1000L)
    return 20
}