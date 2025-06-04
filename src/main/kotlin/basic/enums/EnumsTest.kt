package com.lucas.basic.enums

/**
 * EnumsTest.kt:
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 6. 5. 오전 2:13
 * @description: Kotlin Enum 사용예제
 *
 * - message: Enum 의 프로퍼티
 * - EnumClass.name.value 이런 방식으로 Enum 의 프로퍼티에 접근할 수 있다.
 */

enum class AlertMessage(val message: String){
    SUCCESS("성공"),
    ERROR("오류"),
    WARNING("경고"),
    INFO("정보");
}

fun main() {
    println(AlertMessage.SUCCESS) // Enum 의 이름 출력
    println(AlertMessage.SUCCESS.message) // Enum 의 프로퍼티 접근
}