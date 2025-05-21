package com.lucas.basic.clazz

/**
 * CustomFiled.kt: Custom Field 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 20. 오후 5:04
 * @description: Custom Field 는 body 안에 정의 하여 사용할 수 있음.
 * - get() {} 를 사용하여 getter 를 정의할 수 있다. -> 원래는 내부적으로 자동생성 이지만, 생성자안에 있는것만 해당 된다(속성).
 * - get() 에서는 return 을 사용해 반환될 값을 정의해야 함.
 * - val 일 경우에는 getter 만 정의 가능하다.
 */
class CustomFiled (val height: Int, val weight: Int) {
    // Custom Field 정의
    val isSquare: Boolean
        get() {
           return height == weight
        } // height 와 weight 가 같으면 true, 다르면 false


    // 생성자가 아닌 필드 에서의 get/set 정의
    var isEnabled: Boolean = true
        get() {
            return field // field 는 자동으로 생성된 변수로, getter/setter 에서 사용 가능 -> 내부적으로 사용되는 변수
        }
        set(value) {
            field = value // setter 에서 사용 가능
        } // setter 는 자동으로 생성됨. -> java == this.isEnabled = value; 와 같은 내부 메서드 생성 필요함
}


fun main() {
    val customFiled = CustomFiled(1, 2)
    println("isSquare: ${customFiled.isSquare}") // false
    println("height: ${customFiled.height}") // 1
    println("weight: ${customFiled.weight}") // 2

    // ----------- setter
    val customFiled2 = CustomFiled(2, 2)
    println("isSquare: ${customFiled2.isSquare}") // true
    println("isEnabled: ${customFiled2.isEnabled}") // true

    customFiled2.isEnabled = false
    println("isEnabled: ${customFiled2.isEnabled}") // false


}