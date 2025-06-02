package com.lucas.basic.type

import com.lucas.basic.lambdas.Person

/**
 * NullableType.kt: 코틀린 Nullable 타입 관련 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 6. 2. 오후 5:45
 * @description:
 */


fun main() {
    val nullablePerson: Person? =
        if (System.currentTimeMillis() % 2 == 0L) Person("Alice", 30)
        else null

    val notNullPerson: Person = nullablePerson?: Person("Alice", 30) // nullablePerson 가 null 이면 기본값 Person("Alice", 30) 반환(엘비스 연산자)

    // notNullPerson 사용시 해당 체크는 필요없다 ?
    if(nullablePerson != null) {
        println(nullablePerson.age)
    }

    println(nullablePerson?.age) // 안전 호출 연산자 사용(?.) - null 이면 null 반환, 아니면 age 반환
//    println(nullablePerson!!.age) // 안전 호출 연산자 사용(!!) - null 이면 Exception 발생, 아니면 age 반환 (추천하지 않음)

    println(nullablePerson?.age ?: 0) // 엘비스 연산자 사용(?:) - null 이면 0 반환, 아니면 age 반환
}