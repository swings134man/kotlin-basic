package com.lucas.basic.clazz.opens

/**
 * OpenKeyword.kt: Kotlin Open 키워드 사용 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 28. 오후 6:48
 * @description: override, open 키워드가 붙었다면 상속, 재정의 가능
 * - 그렇지 않다면 상속, 재정의 불가
 * -
 */

interface InputModal {
    fun openModal() // default open
}

// 상속 가능한 Class
open class BaseModal: InputModal {

    protected val notOpen: String = ""
    protected open val openData = ""

    override fun openModal() { // override 상속 가능
        println("Base Modal Opened")
    }

    open fun closeModal(){} // open : 상속 가능, 재정의 가능

    fun describeModal() {} // open 이 없으므로 상속 불가, 재정의 불가
}

// MainModal Class 는 다른곳에서 상속받을 수 없다.
class MainModal: BaseModal() {

//    val notOpen: String = "" // BaseModal 의 notOpen 은 상속되지 않음
    override val openData: String = "Main Modal Data" // openData 는 상속되므로 재정의 가능

    override fun openModal() { // override 재정의 가능
        super.openModal() // 부모 클래스의 openModal 호출
        println("Main Modal Opened")
    }

    override fun closeModal() { // override 재정의 가능
        println("Main Modal Closed")
    }

    // describeModal() 은 상속 불가, 재정의 불가
}

//class TestModal: MainModal() // 불가 open 클래스가 아니기때문임.

fun main() {
}