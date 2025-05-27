package com.lucas.basic.function

/**
 * Extensions.kt: 코틀린 확장함수, infix 함수, Pair, 구조분해선언
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 28. 오전 1:41
 */

// Extension
fun String.double(): String = this + this

// infix function
infix fun String.add(postfix: String): String = this + postfix

fun main() {
    println("bom".double())

    println(setOf("1", "2", "3").maxOrNull()) // Iterable 의 확장함수

    println("Hello".add("bom"))
    println("Hello" add "lucas") // infix function 사용
    // infix 예제
    println(
        mapOf(
            "name" to "lucas",
            "dog" to "bom"
        )
    )

    // Pair
    val (name, age) = Pair("Lucas", 30)
    mapOf(
        "key1" to "lucas",
        "key2" to "bom"
    ).forEach{
        (key, value) -> println("$key : $value")
    }

    // Triple
    val triple = Triple("Lucas", 30, "Developer")
    println("Triple: ${triple.first}, ${triple.second}, ${triple.third}")
}