package com.lucas.basic.clazz

/**
 * User.kt: Entity Class 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 20. 오후 4:41
 * @description: Java 에서는 field/getter/setter 를 사용해야 되지만, 코틀린에서는 한줄로 모든게 처리된다 like.lombok
 * - data 키워드를 추가할 경우 getter/setter/toString/equal/hashCode/copy() 가 자동으로 생성된다.
 * - data class 는 Entity 클래스와 같은 도메인 클래스에서 주로 사용.
 */
class UserEntity (
    var name: String,
    var age: Int,
    var isEnabled: Boolean
)

fun main() {
    val user = UserEntity("Kotlin", 1, true)
    println(user.name)
    println(user.isEnabled)

    user.isEnabled = false // setter 대신 사용하는 방법 -> java == user.setEnabled(false) 과 같은 내부 메서드 생성 필요함
    println(user.name)
    println(user.isEnabled)
}