package com.lucas.basic.clazz.interfaces

/**
 * Clickable.kt: Kotlin 인터페이스, 상속, 구현에 관한 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 28. 오후 6:21
 */

// interface
interface Clickable {
    fun click(): Unit // Abstract

    fun showOff() { // Open - 인터페이스에서 기본 구현 제공 가능
        println("I'm clickable!")
    }
}

// interface2
interface Focusable {
    fun focus()

    // fun showOff() = println("i'm Focus") // 이렇게 다중 인터페이스 구현시, 동일한 기본구현 메서드가 존재할 경우, 무조건 override 해야한다.(구현하는곳에서)
}

class Button: Clickable, Focusable {

    override fun click() { // override 키워드 필수
        println("Button clicked!")
    }

    override fun showOff() { // Open 구현 재정의 가능 - 필수로 작성안해도 된다.
//        super.showOff() // 부모 인터페이스의 기본 구현 호출 - 이럴경우 굳이 구현하지 않아도 됨 -> override 키워드 안먹음
        println("i'm a button!") // 재정의된 구현 - 이럴경우 override 키워드가 필요하다.
        internalPrint() // 해당 클래스 내부에서만 사용 가능하다.
    }

    // Focus Interface
    override fun focus() {
        println("Button focused!")
    }

    private fun internalPrint() { // 해당 Class 내부에서만 사용 가능하다.
        println("Internal Print")
    }
}

fun main() {
    Button().click()
    Button().showOff() // 인터페이스의 재정의 구현 호출
    //Button.internalPrint() // private 메서드는 호출 불가 - 컴파일 에러 발생

    privatePrint() // 같은 File 레벨이기때문에 사용가능
}

private fun privatePrint() {
    println("Private Print")
}