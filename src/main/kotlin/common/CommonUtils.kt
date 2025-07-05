package com.lucas.common

/**
 * CommonUtils.kt: 해당 코틀린 테스트 모듈에서 공통적으로 사용되는 유틸리티 함수들을 정의하는 파일
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 6. 오전 3:53
 */

/**
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 7. 6. 오전 3:54
 * @description: 현재 스레드의 이름과 함께 메시지를 출력하는 함수
 */
fun <T>printlnWithThreadName(message: T) {
    println("$message - ${Thread.currentThread().name}")
}