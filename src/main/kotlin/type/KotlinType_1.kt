package type

/**
 * Kotlin Type1: 코틀린 변수와, 타입의 선언에 대해서 다루는 Class
 */
fun main() {
    println("KotlinType_1");

    /**
     * 1. 타입
     *
     * Kotlin 은 Java 와 다르게 Primitive Type 을 제공하지 않는다.
     *  또한 기본적으로 타입의 첫번째 글자는 대문자로 시작한다.
     *
     *  정수: Long(8), Int(4), Short(2), Byte(1)
     *  실수: Double(8), Float(4)
     *  문자: Char(2)
     *  논리: Boolean(1)
     *  문자열: String
     *
     * 정수 입력시 천단위가 넘어가면 ',' 를 사용하는것 처럼 '_' 를 사용하여 표현 할 수 있다.
     * ex) val long: Long = 1_000_000_000L
     *
     *  U + 타입 : Unsigned 타입
     *  -> 코틀린에서 새로 사용하는 타입이다. (Java 에서는 사용하지 않는 타입)
     *  -> U + 타입은 음수가 없는 타입이다. 즉 정수만 사용한다
     *      -> UInt의 경우 0 ~ 41억 까지의 값을 가질 수 있다. (Int의 경우 -21억 ~ 21억)
     *  종류: ULong(8), UInt(4), UShort(2), UByte(1)
     *
     *  변수 선언시 타입을 생략할 수 있으며, 생략시 타입 추론을 통해 타입을 결정한다.
     *
     *  코틀린은 모든 변수가 객체이다.
     */
    val long: Long = 1234L
    val int: Int = 1234
    val short: Short = 1234
    val byte: Byte = 123
    val double: Double = 123.5
    val float: Float = 123.5f
    val char: Char = 'A'
    val boolean: Boolean = true
    val uInt: UInt = 1234u // U + 타입은 숫자 뒤에 u 를 붙여야 한다.
    println("uInt: $uInt")
    val noInt = 123; // 타입을 생략하면, 타입 추론을 통해 타입을 결정한다.

    println("기존과 같이 \n 을 사용해서 \n 줄바꿈도 가능하다.")
    println("""여러줄의 
            |문자열을
            |출력할때는
            |큰따옴표 3개를 통해서
            |출력할 수 있다.
    """.trimMargin()) // trimMargin 을 통해 | 를 제거할 수 있다.


    // 2. 변수
    /**
     * 2-1 val: 읽기 전용 변수 -> Java의 final 변수
     * 2-2 var: 읽기 쓰기 가능한 변수
     *
     * ex) val name: String = "Kotlin"
     *
     * Kotlin 에는 null 기본적으로 허용 하지 않는다.
     *  -> null 을 허용하려면, 타입에 ? 를 붙여야 한다.
     *    -> nullable 즉 null 을 값으로 가질 수 있는 변수가 된다.
     *    -> nullable 변수를 사용할 때는 반드시 null 체크를 해야 한다.
     *
     *
     */
    var name: String = "Kotlin"
    println("name:" + name)
    println("name: $name") // 위와 아래와 같은 2가지의 변수 활용법이 있다. (String Interpolation)

    // Nullable 변수
    var nullableName: String? = null
//    var nullableName2: String = null // ? 를 붙이지 않으면, null 을 허용하지 않는다.
    println("nullableName: $nullableName")

    // val error
    val errorString: String = "error"
//    errorString = "jitter" // val 은 읽기 전용 변수이므로, 재할당이 불가능하다.
}

