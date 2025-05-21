package com.lucas.basic.enums

import com.lucas.basic.enums.Color.*
import java.time.LocalDateTime
import kotlin.time.Duration

/**
 * Color_when.kt: Kotlin Enum 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 21. 오후 6:31
 * @description: Enum 은 상수 집합을 정의하는데 사용된다.
 * - Enum 을 사용할때 는 enum class 키워드를 사용한다.
 * - Enum 을 사용하는곳에서 switch 문 대신 when 문을 사용하여 더 쉽게 사용 할 수 있다.
 *  - 이때, Enum 의 모든 상수를 사용하거나(정의하거나), else 를 사용하여 처리 하지 않으면 오류가 발생한다!
 *
 *  - 혹은 {} 바디 안에 로직을 정의하여 사용할 수 있다.
 */
enum class Color {
    RED,ORANGE,YELLOW,GREEN
    , BLUE, SORA
    , WHITE
}


// Return Korean color name - By when
// when 인자가 존재하는 함수 여기선 (color)
fun getKorColor(color: Color): String = when (color) {
    RED -> "빨강" // Color.RED -> import 해주면 생략 가능
    ORANGE -> "주황"
    YELLOW -> "노랑"
    GREEN -> "초록"
    BLUE,SORA -> "파랑 || 하늘" // 다중 선택 처리 가능

    else -> "정의되지 않은 색상" // 정의되지 않은 색상(Enum) 처리
}

// when 인자가 존재하지 않는 경우 항상 else 처리 해줘야 한다.
fun mix(c1: Color, c2: Color) =
    when {
        c1 == RED && c2 == YELLOW -> "오렌지"
        c1 == YELLOW && c2 == BLUE -> "녹색"

        else -> throw RuntimeException("Not Color") // 예외처리
    }

// 아무조건없는 경우가 아닌?
fun trueOrFalse(b: Boolean):String = when(b) {
    true -> TODO()
    false -> TODO()
    else -> TODO()
}

// 스마트캐스트: 스마트 캐스트는 컴파일러가 타입을 추론하여 자동으로 캐스팅 해주는 기능 -> 타입검사 + 타입 캐스팅이 동시에 된다
// Java 의 경우 타입 검사 후 -> 명시적 형변환 필요
// 코틀린은 타입 검사할때 자동으로 형변환까지 진행한다(Smart Cast) -> 직접 Casting 도 가능 val dev = Person() as Developer
fun printObj(obj: Any) =
    when(obj) {
        is String -> println("String: ${obj.lowercase()}") // 이런식으로 캐스팅 후 해당 타입으로 바로 사용가능함
        is Int -> println("Int")
        is Color -> println("Color")
        is Duration -> println("Duration: $obj")
        is LocalDateTime -> println("LocalDateTime: ${obj.month}")

        else -> println("Unknown")
    }

// if 문에서도 스마트 캐스트가 가능하다.
fun ifPrintObj(obj: Any): Unit =
    if(obj is String)
        println("String: ${obj.lowercase()}")
    else if(obj is Int)
        println("Int")
    else
        println("Unknown")



fun main() {
    println(getKorColor(YELLOW))
    println(getKorColor(WHITE))
}