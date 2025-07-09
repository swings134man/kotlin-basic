package com.lucas.basic.coroutines

import com.lucas.common.printlnWithThreadName
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * DispathcerCheck.kt: 코루틴의 Dispatcher 에 대한 테스트 샘플
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 10. 오전 12:08
 * @description:
 */
fun main(): Unit = runBlocking {

    // main: 자신을 호출했던 코루틴 scope 에서 Context 를 상속받아 작업하게 된다. main() 의 runBlocking scope 에서 실행되는것.
    launch {
        printlnWithThreadName("Nothing Set")
    }

    // main Thread
    launch(Dispatchers.Unconfined) {
        printlnWithThreadName("Unconfined")
    }

    // DefaultDispatcher-worker-1 :
    launch(Dispatchers.IO) {
        printlnWithThreadName("IO")
    }

    // DefaultDispatcher-worker-1 : 기본 코루틴 스레드
    launch(Dispatchers.Default) {
        printlnWithThreadName("Default")
    }

    // Thread: New-Single-Thread - 비용이 높은 방식, 해당 블록실행시 마다 스레드가 생성됨 : 이방식은 좋지 않아서 아래의 블록을 사용함
//    launch(newSingleThreadContext("New-Single-Thread")) {
//        printlnWithThreadName("new Single Thread Context")
//    }

    // 새로운 Thread 를 만들게 되고 use() 블록이 끝나면 Thread closed() 를 해주게 된다.
    newSingleThreadContext("MyThread").use {
        launch(it) {
            printlnWithThreadName("new Single Thread Context")
        }
    }

}