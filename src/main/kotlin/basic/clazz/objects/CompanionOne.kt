package com.lucas.basic.clazz.objects

/**
 * CompanionOne.kt: kotlin Companion Object 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 26. 오후 10:20
 * @description: Kotlin Companion Object 예제
 */
class CompanionOne {

    companion object {
        // static final 상수는 Companion Object 안에 정의한다.
        const val TAG = "Companion" // static final 런타임 상수 -> 컴파일 타임에 값이 결정됨

        @JvmField
        var staticList: MutableList<String> = mutableListOf() // 변경 가능한 static 가변 리스트 변수

        @JvmStatic
        fun make(text: String) : Unit {
            println("CompanionOne.make() called with text: $text")
        }
    }

}

fun main() {
    // Companion Object 에 접근하는 방법
    println(CompanionOne.TAG) // CompanionOne.Companion.TAG

    CompanionOne.make("Hello, Kotlin Companion Object!") // CompanionOne.Companion.make("Hello, Kotlin Companion Object!")

    CompanionOne.staticList.add("Item 1")
    println("Static List: ${CompanionOne.staticList}")
}
