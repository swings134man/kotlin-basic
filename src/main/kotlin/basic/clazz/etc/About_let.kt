package com.lucas.basic.clazz.etc

/**
 * About_let.kt: Kotlin let 사용에 대한 예제 및 설명
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 8. 5. 오후 9:49
 * @description: let 함수는 객체를 안전하게 다루기 위한 확장 함수로, 객체가 null이 아닐 때만 실행되는 블록을 정의할 수 있다.
 * - 특정 객체가 Null 이 아닐 때만 실행되는 블록을 정의할 수 있어, NullPointerException을 방지하는 데 유용하다. -> 다만 추천되진 않는다
 * - Mutable 한 전역변수를 다룰때 null check 로 사용할때 좋음
 * - 또한 nullable 한 Chaining 에서 유용하게 사용된다.
 */


fun main() {
    // 공식 문서상 사용 예제
//    val listWithNull = listOf("kotlin", "java", null)
//    for (item in listWithNull) {
//        item?.let { println(it)} // null 이 아닐때만 실행됨
//    }

    alsoInsteadLet()
}

/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 8. 5. 오후 9:58
 * @description: main 함수에서 볼 수 있듯 null Checking 을 하여 사용하는 코드
 * 다만 parameter 를 받아, let 을 사용하는것은 권장되지 않음.
 */
fun letNotRecommended(str: String?) {
    str?.let { TODO() } // null 이 아닐때만 실행됨

// 해당 코드는 컴파일시 아래처럼 변환되며, 쓸데없는 변수가 생성되고 작업을 하게됨.
//    if (str != null) {
//        boolean var2 = false;
//        TODO();
//    }

    // 따라서 이런 경우 이런 if 문을 사용하는것이 더 좋다.
    if(str != null) {
        TODO()
    }
}

/**
 * @author: lucaskang(swings134man)
 * @since: 2025. 8. 5. 오후 10:04
 * @description: let 대신 also 를 사용하는 예시
 */
fun alsoInsteadLet() {
    // let 은 scope 의 마지막 값이 return 되기에 it 를 다시 사용해야함
    val list = listOf("kotlin", "java", "c++", null)
    list?.let {
        println("List size: ${it.size}") // it 는 list 를 가리킴
        // 만약 null 을 제거하려면 it.filterNotNull() 을 사용해야함
        it
    }?.forEach { println(it) }

    println("------------------")
    // also 는 scope 의 마지막 값이 return 되지 않기에, it 를 사용하지 않고도 작업을 수행할 수 있다.
    list?.also {
        println("List size: ${it.size}") // it 는 list 를 가리킴
        // 만약 null 을 제거하려면 it.filterNotNull() 을 사용해야함
    }?.forEach { println(it) } // it 는 list 를 가리키지
}