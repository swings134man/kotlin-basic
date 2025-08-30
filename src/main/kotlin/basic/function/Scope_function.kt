package com.lucas.basic.function

/**
 * Scope_function.kt: Kotlin Scope Function (let, run, with, apply, also) 에 대한 설명 및 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 8. 30. 오후 10:31
 * @description:
 * - let: 객체를 it 으로 참조, 마지막 표현식이 반환됨, `null 체크에 유용함`
 * - run: 객체를 this 로 참조, 마지막 표현식이 반환됨, `객체 초기화 및 반환에 유용함`
 * - with: 객체를 this 로 참조, 마지막 표현식이 반환됨, 객체 초기화 및 반환에 유용함, 람다를 인자로 받음 -> 수신객체 를 반환함
 * - apply: 객체를 this 로 참조, 객체 자신이 반환됨 -> 마지막 표현식이 반환되는것이 아님, `객체 수정에 유용함` -> 수신객체 를 반환함
 * - also: 객체를 it 으로 참조, 객체 자신이 반환됨 -> 마지막 표현식이 반환되는것이 아님, `객체 수정에 유용함`
 * ------------------------------------------------------------------------------------
 * # 사용 예시
 * let: null 체크, Chaining
 * also: 객체 사이드 이펙트 확인, 데이터 유효성 검사
 * run: 객체 초기화 및 반환, 어떤 값을 계산할 필요가 있거나, 지역변수 범위 제한시
 * with: null 이 될 수 없는 객체에 대해서 여러 작업 수행, 결과가 필요 없을떄
 * apply: 객체 수정, 수신객체 자신 반환시
 *
 */

fun main() {
    val person = Person("John", 30)

    // let: 객체를 it 으로 참조, 마지막 표현식이 반환됨
    val letResult = person.let {
        println("Let - Name: ${it.name}, Age: ${it.age}")
        it.age + 5 // 마지막 표현식이 반환됨
    }
    println("Let Result: $letResult")

    // run: 객체를 this 로 참조, 마지막 표현식이 반환됨
    val runResult = person.run {
        println("Run - Name: $name, Age: $age")
        age + 10 // 마지막 표현식이 반환됨
    }
    println("Run Result: $runResult")

    // with: 객체를 this 로 참조, 마지막 표현식이 반환됨
    val withResult = with(person) {
        println("With - Name: $name, Age: $age")
        age + 15 // 마지막 표현식이 반환됨
    }
    println("With Result: $withResult")

    // apply: 객체를 this 로 참조, 객체 자신이 반환됨 -> 마지막 표현식이 반환되는것이 아님
    // 객체 를 수정하여 이후 모든 person 변수에 영향이 미침 => age:30 => 31 로 나옴
    val applyResult = person.apply {
        println("Apply - Name: $name, Age: $age")
        age += 1 // 객체의 속성 변경 가능
    }
    println("Apply Result - New Age: ${applyResult.age}")

    // also: 객체를 it 으로 참조, 객체 자신이 반환됨 -> 마지막 표현식이 반환되는것이 아님
    val alsoResult = person.also {
        println("Also - Name: ${it.name}, Age: ${it.age}")
        it.age += 2 // 객체의 속성 변경 가능
    }
    println("Also Obj: ${alsoResult}")
    println("Also Result - New Age: ${alsoResult.age}")
}

private data class Person(var name: String, var age: Int)