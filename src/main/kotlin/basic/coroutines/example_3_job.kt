package com.lucas.basic.coroutines

import com.lucas.common.printlnWithThreadName
import kotlinx.coroutines.*


/**
 * example_3_job.kt: 코루틴의 Job 객체와, Cancel 기능에 관한 파일
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 4:55
 * @description:
 */

fun main() {
//    cancelJob()
//    cancelFail()
    cancelFlag()
}


/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 5:00
 * @description: 코루틴 Cancel 샘플: 1초에 2번씩 출력하는 코루틴이 실행, main 코루틴은 1.3초 후에 해당 코루틴을 취소하고, join() 으로 완료를 기다린다.
 */
fun cancelJob() = runBlocking{
    val job = launch {
        repeat(1000) { // Coroutine#2
            printlnWithThreadName("job : $it")
            delay(500L) // 0.5s
        }
    }

    delay(1300L)
    printlnWithThreadName("Main: Waiting Job") // Coroutine#1

    job.cancel()
    job.join()
    printlnWithThreadName("main : job cancelled")
}

/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 5:08
 * @description: 코루틴 취소가 되지 않는경우 - 코루틴 취소의 경우 '중단지점' 이 있어야 취소가 가능하다.
 * - 중단지점: flag 가 있거나, delay 같은 중단지점이 있는경우 (suspend function)
 * - 해당 function 내부에는 중단지점이 없기 때문에, cancel() 이 되지 않는다.
 * - yield() 의 경우, 지연없는 중단지점을 만들어 주지만, Dispatchers.Default + IO 내부에서 실행될때만 의미있는 중단지점이 된다. -> 나머지는 동작하지 않음
 *  -> 스레드가 2개이상인 디스패처에서 의미가 있다는 것.
 */
fun cancelFail() = runBlocking{
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        try {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                if(System.currentTimeMillis() >= nextPrintTime) {
//                delay(1L) // 중단지점이 존재할 경우 cancel 가능
                yield() // delay(1L) 을 주지 않아도 중단지점(suspend) 를 만들수 있음. -> cancel() 호출될시 Exception 을 발생시킨다.(JobCancellationException)
                    printlnWithThreadName("job : ${i++}")
                    nextPrintTime += 500L // 0.5s
                }
            }
        }catch (e: Exception) {
            println("Exception: $e")
        }
    } // job

    delay(1300L)
    printlnWithThreadName("Main: Waiting Job") // Coroutine#1

    job.cancelAndJoin()
    printlnWithThreadName("main : job cancelled")
}//fun



/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 5:20
 * @description: 위와 같은 취소를 할 수 있는 경우 : Flag 사용한 예시
 * isActive 의 경우 코루틴의 상태를 체크하는 내부 변수로, 코루틴이 활성화되어 있는지 여부를 확인할 수 있다.
 * - 코루틴이 활성화되어 있다면 true, 그렇지 않다면 false 를 반환한다.
 * - 별도의 Exception 을 발생시키지는 않는다.
 *
 * > 1.3 초 이후 cancelAndJoin() 이 호출되고, 코루틴의 작업이 취소된다면 -> isActive == false -> while 문을 종료한다.
 */
fun cancelFlag() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        try {
            var nextPrintTime = startTime
            var i = 0
            println("Job is active: $isActive") // true
            while (isActive) {
                if(System.currentTimeMillis() >= nextPrintTime) {
                    printlnWithThreadName("job : ${i++}")
                    nextPrintTime += 500L // 0.5s
                }
            }
            println("Job is active: $isActive") // false
        }catch (e: Exception) {
            println("Exception: $e")
        }
    } // job

    delay(1300L)
    printlnWithThreadName("Main: Waiting Job") // Coroutine#1

    job.cancelAndJoin()
    printlnWithThreadName("main : job cancelled")
}

/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 5:33
 * @description: TimeOut 을 지정하여, 해당 시간 이후 에 코루틴을 취소하는 예시
 * - TimeOutCancellationException 이 발생한다.
 * -> withTimeoutOrNull 을 사용하면 0> exception 이 발생하지 않고 null 을 반환한다.
 */
fun timeOutCancel() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("job : $i")
            delay(500L)
        }
    }
}

/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 7. 오후 6:16
 * @description: 코루틴이 Cancel 되었지만 취소된 코루틴 내부에서 코루틴을 실행해야하는 예시
 * -> Run non -cancellable block 을 사용하여, 코루틴이 취소되었더라도 해당 블록 내부의 코드는 실행된다.
 */
fun cancelNonCancellable() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job : $i")
                delay(500L)
            }
        } finally {
            println("Job is cancelled, but running non-cancellable block")
            withContext(NonCancellable) {
                // 코루틴은 취소되었지만 내부적으로 또 호출하여 코루틴을 실행하도록 함
                delay(1000L) // 1초 동안 실행되는 블록
                println("Non-cancellable block completed")
            }
        }
    }

    delay(1300L)
    println("Main: Cancelling Job")
    job.cancelAndJoin()
    println("Main: Job cancelled")
}


