package com.lucas.basic.clazz.constructors

/**
 * Constructors.kt: Kotilin에서 생성자(Constructor)와 관련된 예제 코드입니다.
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 29. 오전 1:38
 */

open class ParentClass(val familyName: String)

// Kotlin 스타일
class ChildClass(val subName: String,
    familyName: String // 부모 클래스의 필드는, val,var 를 붙이지 않는다.
    ): ParentClass(familyName = familyName){ // () 는 주생성자(Primary Constructor)

        // sub 생성자(Secondary Constructor)
        constructor(subName: String) : this(subName, "")

    }

// Java 스타일 - 이렇게 잘 사용하지는 않는다 ~
class ChildClassJava : ParentClass {

    private val subName: String

    // 주 생성자 - super 키워드로 부모 클래스의 생성자를 호출한다.
    constructor(subName: String, familyName: String) : super(familyName) {
        this.subName = subName
    }

    // 부 생성자
    constructor(subName: String) : this(subName, "")
}

// 초기화 블록 사용방법 - 객체생성시 호출되는것
class InitChildClass(name: String) {
//    val userName = name // 이렇게도 가능
    val userName: String // 프로퍼티 선언과 초기화 분리 -> 자바에서 이렇게 많이 사용했었음.

    // 초기화 블록
    init {
        userName = name
    }
}

// 초기화 블록 대체 - 프로퍼티와 주생성자를 사용해 한번에 초기화
class InitChildClass2(val name: String) {}