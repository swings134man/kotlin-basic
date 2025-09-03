package com.lucas.basic.clazz.inner

/**
 * InnerClass1.kt: Kotlin Inner Class 에 대한 설명 및 예제 1
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 9. 3. 오후 5:08
 * @description: 
 */
class InnerClass1 {

    var a = 10

    fun a() {
        println("A")
        // B 클래스의 멤버에 접근 불가
        // println(b) // Error
        // 사용하려면 val bInstance = B() , bInstance.b 로 접근해야함
    }

    fun a2() {
        // Inner Class  인 C 의 멤버에 접근 가능
        val cInstance = C().c
        println(cInstance)
    }


    // 중첩 클래스 (Nested Class): 기본적으로 static 으로 생성된다. 따라서 외부 클래스의 인스턴스 없이도 접근 가능
    // public static class
    class B {

        var b = 20

        fun b() {
            println("B Nested Class")
        }

        fun c() {
            // 외부 클래스의 멤버에 접근 불가
            // println(a) // Error
        }
    }

    // 내부 클래스 (Inner Class): inner 키워드를 사용하여 정의, 외부 클래스의 인스턴스에 접근 가능
    // public class
    inner class C {

        var c = 30

        fun d() {
            println("C Inner Class")
            println(a) // 외부 클래스의 멤버에 접근 가능

            // this@Class 로 외부 클래스, 내부 클래스에 접근 가능(명시적)
            this@InnerClass1.a = 20
            this@C.c = this@InnerClass1.a

            println(a)
            println(c)
        }
    }
}

fun main() {
    // Nested Class 는 외부 클래스의 인스턴스 없이도 접근 가능
    val b = InnerClass1.B()
    b.b()

    // Inner Class 는 외부 클래스의 인스턴스를 통해 접근해야 함
    val innerClass1 = InnerClass1()
    val c = innerClass1.C()
    c.d()

    innerClass1.a2() // a2() 에서 새로운 인스턴스를 생성하므로 30 출력
}