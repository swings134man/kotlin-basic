package com.lucas.basic.coroutines

import com.lucas.common.printlnWithThreadName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * example_2_suspend.kt: Coroutine - suspend 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 6. 오전 2:25
 * @description: suspend 함수는 코루틴 실행을 "일시중지" 하고, 중단지점 부터 나중에 다시 재개할 수 있는 함수. -> 코루틴 안에서만 호출 가능한 함수.
 * java 에서의 Thread.sleep() 시에는 Thread 를 Blocking 하여 다른 작업을 수행 X -> 기다리는동안 자원 낭비 발생함.
 * - 코루틴에서는, 코루틴만 일시중지 되고, 그 시간동안 다른 코루틴이 해당 스레드를 사용하여 다른 작업을 수행할 수 있다.
 *
 * `고성능 고효율` 방식을 위한것.
 * `코루틴 Scope` 내부에서만 사용가능. -> main() 안에서 직접 호출불가 -> main() = runBlocking() 안에서는 가능! 코루틴 Scope 내부이기때문
 *
 * - task 1,2,3 가 있고 (1) 이 suspend(중단) 된다면, (2),(3) 을 실행하고 (1)에서 중단되었던 지점부터 작업을 재개 하는 방식이다
 *
 * --------------------------------------------------------------------------------------------------------------------------------
 * - Extract 시 주의: main 함수는 코루틴빌더이고, launch 안에서 다른 함수를 호출하는데 delay() 가 있다면, 해당 함수는 suspend 함수로 정의되어야 한다.
 * -> Coroutine Scope 내에서 호출하기 때문에, delay() 가 있는 함수는 suspend 함수로 정의되어야 한다.
 *
 * - suspend (중단지점) 이 없는경우 (무한루프) cancel 되지 않는다.
 */
fun main(): Unit = runBlocking {
    launch { task1() } // @Coroutine#2
    launch { task2() } // @Coroutine#3
    printlnWithThreadName("Main Start") // @Coroutine#1

//    main2()
}


suspend fun task1() {
    printlnWithThreadName("Start 1")
    delay(1000L)
    printlnWithThreadName("End 1")
}

suspend fun task2() {
    printlnWithThreadName("Start 2")
    delay(1000L)
    printlnWithThreadName("End 2")
}
// ---------------------------------------------------------------------------
fun main2() = runBlocking {
    printlnWithThreadName("Main Start")

    val job1 = launch { looop() }

    delay(1000L)
    job1.cancel()

    printlnWithThreadName("Main End") // @Coroutine#5

}

suspend fun looop() {
    for (i in 1..1000) {
        println("Looping $i")
        delay(100L) // 중단지점이 없을 경우 cancel 되지 않고 끝까지 실행된다.
    }
}
