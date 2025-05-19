package basic.function

/**
 * Kotlin Function1: 코틀린 함수에 대해서 다루는 Class
 */

fun main() {
    exam1()
    exam1("test")

    val examReturn: String = examReturn()
    println("examReturn: $examReturn")

//    var examParam = examParam("Kotlin1", 1) // 파라미터는 순서에 맞춰 작성하기도 하지만.
    val examParam = examParam(age = 1, name = "Kotlin1") // 파라미터의 이름을 지정하여 작성할 수도 있다.
    println("examParam: $examParam")

    val examParamDefault = examParamDefault()
    println("examParamDefault: $examParamDefault")

}//main

/**
 * 1. 함수
 * 1-1. 함수의 선언
 *  - fun 함수명(파라미터): 반환타입 {}
 *
 * 1-2. 함수의 호출
 *  - 함수명(파라미터)
 *
 * 1-3. 함수의 반환
 *  - 반환타입을 생략하면 반환하지 않는다.
 *  - 명시적으로 표현하고자 할때는 Unit 을 사용한다. (Java의 void 와 같다)
 *
 * 1-4. 함수의 파라미터
 *  - 파라미터의 타입을 생략하면, 타입 추론을 통해 타입을 결정한다.
 *  - 파라미터의 기본값을 지정할 수 있다.
 *
 *
 */

// return 이 없는 함수
fun exam1(): Unit {
    println("exam1: No Return = Unit")
}

// Overloading
fun exam1(name: String): Unit {
    println("exam1: No Return = Unit, name: $name")
}

// return 이 있는 함수
fun examReturn(): String {
    return "Return String"
}

// 파라미터가 있는 함수
fun examParam(name: String, age: Int): String {
    return "name: $name, age: $age"
}

// 파라미터의 기본값을 지정할 수 있다.
// 만약 파라미터에 값이 없다면 기본값으로 사용된다.
fun examParamDefault(name: String = "Kotlin2", age: Int = 2): String {
    return "name: $name, age: $age"
}

// 함수 내부의 함수
fun examInnerFunction(): Unit {
    fun innerFunction(): Unit {
        println("innerFunction")
    }
    innerFunction() // 함수 내부에서 선언된 함수는 함수 내부에서만 사용할 수 있다. 함수 외부에서 호출이 불가능.
}

fun examInnerFunction2(): Unit {
    //innerFunction() // 함수 내부에서 선언된 함수는 함수 내부에서만 사용할 수 있다. 함수 외부에서 호출이 불가능.
}