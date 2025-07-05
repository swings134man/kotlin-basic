package com.lucas.basic.coroutines

import com.lucas.common.printlnWithThreadName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * example_1.kt: Kotlin Coroutine Example 1 번 예제 - 코루틴 공식 Docs 기준 샘플
 * - 각 함수별로 구조적으로 사용하기 좋은 코루틴의 방식을 설명하고 있음.
 * - Top-Level 코루틴을(builder) 를 만들지 않고
 *   - 해당 코루틴의 child 를 만들어서 사용하는게 BEST Practice 이다. -> 구조적 동시성(Structured Concurrency) 이라고 함.
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 2. 오후 5:23
 * @description: 해당 main 에 대한 동작설명
 * - GlobalScope.launch 블록 안에 있는 코드는 비동기적으로 실행됩니다. -> 코루틴 실행 블록
 *      - GlobalScope 사용시, warning 을 받게 되며, 이는 GlobalScope 가 프로그램 전체에서 실행되는 코루틴을 생성하기 때문입니다.
 *      - GlobalScope 는 프로그램 전체에서 실행되는 코루틴을 생성하기 때문에, 구조적 동시성(Structured Concurrency) 에 위배됩니다. -> 따라서, 오용되기 쉬운 API 이며, 구조적 동시성 위반할 수 있기 때문에 경고하는것이고
 *      - @optIn 을 사용하여 잠재적인 위험이 존재한다는것을 명시적으로 표시한다.
 *
 * - 우선적으로 main 함수가 실행되면서 "Hello"를 출력.
 *      - 이후에 GlobalScope.launch 블록 안의 코드가 실행되기 전에 main 함수가 종료되지 않도록 Thread.sleep(2000L)로 2초간 대기합니다.
 * - 1s 후에 코루틴 Scope 안에 코드가 실행됨. -> "World!!" 출력
 *
 * - delay == suspend 함수, Thread.sleep 은 blocking 함수
 * - 하지만 delay 는 코루틴 scope 내에서만 사용 가능함.
 *
 * - runBlocking 은 코루틴 빌더로, 코루틴을 실행하고 완료될 때까지 현재 스레드를 블로킹한다.
 *      - 다만 동기적 Blocking 이기 때문에, 비동기를 사용하는 코드 내부에서는 사용하지 않음.
 */
fun main() {

//    example1()
//    example1Sub()
//    example2Sub()
    example3Sub()

}

@OptIn(DelicateCoroutinesApi::class) // DelicateCoroutinesApi: GlobalScope 를 사용하겠다는 명시적인 표시
fun example1() {
    // coroutine 실행 블록 -> GlobalScope 는 object
    GlobalScope.launch {
        delay(1000L)
        printlnWithThreadName("World!!") // defaultDispatcher-worker-1
    }

    printlnWithThreadName("Hello") // main

    // Blocking 함수 (Main Thread)
//    Thread.sleep(2000L)

    // delay 로 변경(suspend, 코루틴 scope) -> 코루틴 빌더이고, Thread 를 Blocking 함.
    runBlocking {
        println(" ------------------ runBlocking(${Thread.currentThread().name}) ------------------ ")
        delay(2000L)
    }
}

/**
 * 위의 Main() 을 runBlocking{} 으로 감싸면, 코루틴이 실행되기 전에 main 함수가 종료되지 않도록 합니다.
 * 결국 main 이 종료되지 않도록 하기 위해서, 다 실행되기전까지 main Thread 를 블로킹하는 역할을 한다.
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 2. 오후 5:49 
 */
@OptIn(DelicateCoroutinesApi::class)
fun example1Sub() = runBlocking {

    GlobalScope.launch {
        delay(1000L)
        printlnWithThreadName("World!!") // defaultDispatcher-worker-1
    }

    printlnWithThreadName("Hello") // main
    delay(2000L)
}


/**
 * - 다만 delay 를 사용하는것은 좋은 접근방식이 아니다.
 * - example1Sub() 에서 delay 가 없다면, 프로그램이 바로 종료 된다. 혹은 GlobalScope 안의 delay 가 3초라면? 제대로 동작하지 않는다.
 * - 이떄 job 을 사용하여 child 를 기다리게 할 수 있다.
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 2. 오후 5:51 
 */
@OptIn(DelicateCoroutinesApi::class)
fun example2Sub() = runBlocking {
    val job = GlobalScope.launch {
        delay(3000L) // 3초 후에 실행 -> 원래대로 라면 프로그램이 종료되기 때문에, 이 부분이 실행되지 않음. -> 다만 join() 을 사용해서 기다리게 한다.
        printlnWithThreadName("World!!")
    }

    printlnWithThreadName("Hello")

    job.join() // job 이 완료될 때까지 기다립니다.
}


/**
 * - 구조적 동시성(Structured Concurrency) 의 예시
 *
 * - 다만 위의 코드는 join() 이 없을 경우, 프로그램이 종료됨.
 * - 즉 구조적으로 GlobalScope 와 runBlocking 은 구조적으로 관련이 없다. -> 별도의 코루틴 scope 임
 * - 2개의 launch 블록이 실행되지만. join 을 사용하지 않더라도, 2개의 launch 블록 모두 기다렸다가 종료하게 됨.
 * - 등록은 순차적이지만, -> 실행은 병렬이기떄문에 world 1,2 순차적으로 출력되지 않을 수 있음.
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 2. 오후 5:59 
 */
fun example3Sub() = runBlocking {
    this.launch {
        delay(1000L)
        printlnWithThreadName("World 1 !!")
    }

    this.launch {
        delay(1000L)
        printlnWithThreadName("World 2 !!")
    }

    printlnWithThreadName("Hello")
}
