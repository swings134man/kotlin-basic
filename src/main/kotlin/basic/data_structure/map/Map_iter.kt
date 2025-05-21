package com.lucas.basic.data_structure.map

/**
 * Map_iter.kt: Kotlin 에서의 Map 과 Iterator 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 21. 오후 11:24
 * @description: Kotlin 에서의 Map 과 Iterator 예제
 *
 * - Map 은 key-value 쌍으로 이루어진 데이터 구조이다.
 * - Kotlin 에서는 mutableMap(변할수있는), immutableMap(변할수없는) 두가지가 있다.
 * - mutableMap 은 put() 과 remove() 를 사용하여 값을 추가하거나 삭제할 수 있다.
 * - immutableMap 은 값을 변경할 수 없다. -> val 로 선언된 Map 은 immutableMap 이다.
 *
 * - map 을 간단하게 생성해주는 컬렉션 함수를 사용하는 방식임
 * - Java 에서는 map.put("","") 과 같은 방식 이지만
 *      - 코틀린에서는, map[key] = value; 와 같은 방식으로 사용 -> 코틀린 스러운 방식임
 */

fun main() {

    val students = mutableMapOf<Int, String>() // 내부적으로 LinkedHashMap 으로 구현되어 있음
    // mapOf() 를 사용하여 더 빠르게 생성할 수 있음 -> 다만 ImmutableMap 이므로 값을 변경할 수 없다.
    //val students2 = mapOf<Int, String>(1 to "Kotlin", 2 to "Java", 3 to "Python")

    // put
    students[1] = "Kotlin"
    students[2] = "Java"
    students[3] = "Python"

    for (i in students) {
        println("번호: ${i.key}, 이름: ${i.value}")
    }

    println("---------------------")

    //자바에서는 -> for (entry in map.entries)
    // 각 k.v 를 구분하여 제어 할 수 있도록 함(구조분해)
    for ((k, v) in students) {
        println("번호: $k, 이름: $v")
    }

    println("---------------------")

    println("a 는 ${recognize('a')}")
    println("3 는 ${recognize('3')}")

}


// in 키워드로 범위 검사도 가능함
// in 키워드는 contains() 를 호출하여 범위 검사를 한다.
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z' // a~z, A~Z -> 내부적으로 a<=z && A<=Z

fun isNotDigit(c: Char) = c !in '0'..'9' // 0~9 가 아닌경우

// c 변수가 숫자인지, 문자열인지 판별
fun recognize(c: Char) =
    when (c) {
        in '0'..'9' -> "숫자"
        in 'a'..'z', in 'A'..'Z' -> "영문자"
        else -> "기타"
    }
