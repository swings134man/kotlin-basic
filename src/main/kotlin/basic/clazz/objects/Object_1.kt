package com.lucas.basic.clazz.objects

/**
 * Object_1.kt: Kotlin Object 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 26. 오후 6:51
 * @description: Kotlin Object 예제
 * - Object 는 각각 Singleton 객체이다.
 * - 생성자 사용 불가.
 * - 확장 함수 정의 불가 -> object 자체는 싱글톤 인스턴스이고, 확장의 형태보다는 정의되어있는 것을 사용하는것임(내부 구현 고정)
 *   - object 는 "단일 인스턴스" 이다.
 */
// Class 외부에 위치하여야 한다.
object Singleton {
    // 싱글톤 객체는 클래스처럼 프로퍼티와 메소드를 가질 수 있다.
    var name: String = "Kotlin Singleton"

    fun printName() {
        println("Name: $name")
    }
}

object SingletonUtil {
    fun printUtil(text: String) {
        println("This is a utility function in SingletonUtil: $text")
    }
}


// Object 는 상속 및 인터페이스 구현이 가능하다.
interface SingletonInterface {
    fun printName()
}

object SingletonWithInterface : SingletonInterface {
    override fun printName() {
        println("This is a singleton implementing an interface.")
    }

    // 추가적인 프로퍼티나 메소드도 정의할 수 있다.
    fun additionalFunction() {
        println("This is an additional function in SingletonWithInterface.")
    }
}


fun main() {
    println(Singleton.name)

    Singleton.printName()


    // Utils
    SingletonUtil.printUtil("TEXT!!!!")

    // interface
    SingletonWithInterface.printName()
    SingletonWithInterface.additionalFunction()
}