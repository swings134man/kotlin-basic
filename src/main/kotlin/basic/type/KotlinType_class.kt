package basic.type

/**
 * Kotlin Type1: 코틀린 변수와, 타입의 선언에 대해서 다루는 Class
 * KoPring 사용 했을 때는 Java 의 문법과 비슷하게 사용했었음.2024.05
 */
class KotlinType1 {
    private val name: String = "KotlinType1Name"

    fun getName() {
        println("name: $name")
    }
}

// Constructor Test(생성자에는 여러가지 종류가 지원되는데 아래 방식은 Primary Constructor(주생성자) 를 사용한 방식이다.)
// val/var 와 초기화를 함께 한다면 본문의 내용은 생략 가능함. val lastName: String = ""
class KotlinConstTest(private val firstName: String, private val lastName: String) {
    // 주생성자를 통해 선언된 변수는 클래스 내에서 사용이 가능하다.
    private var fullName: String = "$firstName $lastName"
    init {
        println("Primary Constructor: $firstName $lastName")
    }

    fun getFullName() {
        println("fullName: $fullName")
    }
}

// Secondary Constructor(보조생성자) 를 사용한 방식
class KotlinConstSecondaryTest {
    private var fullName: String

    constructor(firstName: String, lastName: String) {
        println("Secondary Constructor: $firstName $lastName")
        this.fullName = "$firstName $lastName"
    }

    fun getFullName() {
        println("Secondary fullName: $fullName")
    }
}


fun main() {
    println("KotlinType1")
    KotlinType1().getName()

    // Constructor Test
    val constObj = KotlinConstTest("first", "last")
    constObj.getFullName()

    // Secondary Constructor Test
    val constSecondaryObj = KotlinConstSecondaryTest("second", "name")
    constSecondaryObj.getFullName()

    // 지역 class: 함수 내부에 선언된 class(nested Class)
    class LocalClass {
        fun print() {
            println("LocalClass print !!!!")
        }
    }
    LocalClass().print()
}
