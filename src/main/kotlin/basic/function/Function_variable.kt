package basic.function

/**
 * Function_variable.kt: Kotlin 의 다양한 Function form
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 19. 오후 11:07
 */

// 1. Inline Function
fun sum(x: Int, y: Int): Int = x + y

// 2. Return Type 생략 - 명시적으로 지정하지 않으면 혼동올 수 있음
fun sum2(x: Int, y: Int) = x + y

// 3. Return Type X - Java 의 void
fun sum3(x: Int, y: Int): Unit {
    println("sum3: $x + $y = ${x + y}")
}

// 4. Like Java - Body Return
fun sum4(x: Int, y: Int) : Int {
    return x + y
}


fun main() {
    println("sum: ${sum(1, 2)}")
    println("sum2: ${sum2(1, 2)}")
    sum3(1, 2)
    println("sum4: ${sum4(1, 2)}")
}