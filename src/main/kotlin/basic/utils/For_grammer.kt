package com.lucas.basic.utils

/**
 * For_grammer.kt: Kotlin 에서의 For 문 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 21. 오후 11:18
 * @description: Kotlin 에서의 For 문 예제
 *
 * - .. : 범위 연산자 = 시작값 부터 끝값 까지 포함 (<=)
 * - until: 마지막 값 제외 (<)
 * - downTo: 역순으로 반복 (10 부터 1까지, >=)
 * - step: 증가폭을 조절할 수 있다. -> i+=2 는 step 2 로 표현 가능함
 */

fun main() {
    // 1. for 문
    for (i in 1..10) { // 1 부터 10 까지 -> <= 10
        println("i: $i")
    }

    for (i in 1 until 10) { // 1 부터 9 까지 -> < 10
        println("i: $i")
    }

    for (i in 10 downTo 1) { // 10 부터 1 까지 -> >= 1
        println("i: $i")
    }

    for (i in 1..10 step 2) { // 1 부터 10 까지 2씩 증가 -> for(int i = 1; i <= 10; i+=2)
        println("i: $i")
    }

    // step 을 사용하면 증가폭을 조절할 수 있다. -> i+=2 는 step 2 로 표현 가능함
    for (i in 1 until 10 step 2) { // 1 부터 9 까지 2씩 증가 -> for(int i = 1; i < 10; i+=2)
        println("i: $i")
    }
}