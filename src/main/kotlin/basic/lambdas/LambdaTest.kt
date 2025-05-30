package com.lucas.basic.lambdas

/**
 * LambdaTest.kt: Kotlin Lambda 사용 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 29. 오후 10:04
 */

// 일반함수
fun sumVal(x: Int, y: Int): Int = x + y

// 람다 변수
val sumLambda = {x: Int, y: Int -> x + y}


// Class - 람다 사용 샘플 클래스
data class LambdaClass(val name: String, var age: Int)


fun main() {
    println(sumVal(1,2)) // 일반 함수 호출
    println(sumLambda(1,2)) // 람다 변수 호출
    println({x: Int, y: Int -> x + y}(1, 2)) // 람다 표현식 호출

    // -------------------- Class ---------------------
    val persons = listOf(
        LambdaClass("Alice", 30),
        LambdaClass("Bob", 25),
        LambdaClass("Charlie", 35)
    )

    // 가장 나이가 많은 사람 찾기 - Lambda 는 여러가지로 사용할 수 있음
    persons.maxByOrNull({person: LambdaClass -> person.age})
    persons.maxByOrNull {person: LambdaClass -> person.age}
    persons.maxByOrNull {person -> person.age}
    persons.maxByOrNull { it.age } // it 키워드 사용 - 람다 파라미터가 하나일 때
    persons.maxByOrNull (LambdaClass::age) // Method Parameter Reference 사용

}