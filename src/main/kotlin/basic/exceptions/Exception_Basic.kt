package com.lucas.basic.exceptions

import java.io.FileReader
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Exception_Basic.kt: Kotlin 에서의 Exception 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 22. 오후 5:28
 * @description: Kotlin 에서의 Exception 예제
 * - 일반적으로 Exception 에 관련한건 Java 와 거의 동일하다.
 * - try/catch 문은 값으로 취급되어 사용 할수도있다. (like. when, if)
 * - throws ... 와 같은 Checked Exception 을 붙이지 않는다. -> 자바의 경우 컴파일 오류 발생
 *
 *
 * - Kotlin 에서는 Checked Exception 에 대한 처리를 강제하지 않는다. -> Java 에서는 강제함(throws ... , try/catch)
 *      - Checked Exception 을 올바른 에러 핸들링 하는 경우가 드물다. -> 보통 그냥 Exception 이 던져지고, 그대로 에러가 나게 하는 경우가 많다.
 *      - 또한 LSP (리스코프 치환 원칙) 에 위배되는 경우가 많다. -> 자식이 부모를 대체할 수 있어야 하는데, 부모는 안던지고 자식만 예외를 던지는 경우 (호환성 깨짐)
 *      - 추세또한, Unchecked Exception 으로 변경하는 추세이다
 *          - 처리를 강제하지 않음으로 코드가 간결해짐 (throws, try/catch 사용 X)
 *          - 호출하는 쪽에서, 원하는 특정 Exception 에 대해서만 처리 할 수 있음.
 *          - 비지니스/시스템 예외를 나눠서 명확히 처리 가능함. (RunTime == Application 동작 중 예외)
 *          - @Transactional 의 경우 UnChecked 는 Rollback -> 자연스럽고, 예외 정책 통일성 측면에서 좋음
 *
 *  - 주의점: Java 와 동시 사용시 주의해야함 -> Java 에서는 Checked Exception 을 강제하기 때문
 *      - Checked 는 @Transactional 에서 Rollback 을 하지 않음. -> 예외가 발생하였음에도 Commit 발생 할 수 있음.
 *
 *  - Kotlin 에서는 Checked 가 발생할것 같은곳에 try/catch 를 사용하는것이 안전함? -> 가급적 사용하지 않는게 좋음
 */
fun main() {
    try {
        Integer.parseInt("123")
    }catch (e: Exception){
        throw IOException("Checked Exception") // Exception 을 Throw 할때 Method 에 throws 를 붙이지 않아도 된다. -> IOException 은 Checked Exception 이므로
    }finally {
        println("finally")
    }

    exceptionTest()

    // checked
//    checkedExceptionTest()
    checkedFileException()

    println("---------------- END ------------------") //UnReachable Code
}


@Throws(IOException::class) // 명시적으로 Throws 처리할 수 있음 (변환시 throws IOException)
fun exceptionTest() {
    try {
        Integer.parseInt("ㅁㄴ")
    }catch (e: Exception){
        println("ExceptionTest() : Exception")
    }finally {
        println("ExceptionTest() : finally")
    }
}

// 가급적 Checked Exception -> Unchecked Exception(Runtime) 으로 변경하여 사용 하는게 좋다. -> 더이상 로직이 진행되지 않도록
fun exceptionTest2() {
    try {
        Integer.parseInt("123")
        throw IOException() //Checked Exception
    }catch (e: IOException){
        throw RuntimeException("Unchecked Exception") // IOException(Checked) 이면 Unchecked Exception(RunTime) 으로 변경
    }finally {
        println("ExceptionTest2() : finally")
    }
}

fun checkedExceptionTest() {
    Integer.parseInt("123")
    throw Exception("Checked Exception") // Checked Exception
}

fun checkedFileException() {
    FileReader("test.txt").close() // FileNotFoundException
}