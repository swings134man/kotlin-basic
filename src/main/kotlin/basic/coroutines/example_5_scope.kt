package com.lucas.basic.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * example_5_scope.kt: 코루틴의 Scope 에 대한 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 10. 오전 12:37
 * @description: 공통적으로 사용되는 Scope 의 경우, Scope 정의하고, 해당 Scope 내에서 코루틴을 실행하는 방식으로 사용한다.
 * - 종료 시그널을 받아, 해당 Scope 내에서 실행되는 코루틴들을 모두 취소할 수 있다.
 * - 공통적으로 사용되는 것들에서 사용되는 방식 -> 안드로이드의 Main Activity -> 특정화면에서 코루틴을 실행하던 도중 화면을 나간다면 모두 정지 되는 방식
 */
class example_5_scope {

    // 코루틴 Scope 정의
    private val scope = CoroutineScope(Dispatchers.Default)

    // Scope 종료 시, 해당 Scope 내에서 실행되는 모든 코루틴을 취소한다.
    fun destroy() {
        scope.cancel()
    }

    fun doSomething() {
        repeat(10) { i ->
            scope.launch {
                delay((i + 1) * 200L) // 200ms, 400ms, 600ms, ...
                println("Task $i Completed")
            }
        }
    }

}


fun main(): Unit = runBlocking {
    val exampleScope = example_5_scope()

    // doSomething() 을 호출하면, 10개의 코루틴이 실행된다.
    exampleScope.doSomething()

    // 1초 후에 Scope 를 종료한다.
    delay(1000L)
    exampleScope.destroy()

    println("All tasks cancelled")
}