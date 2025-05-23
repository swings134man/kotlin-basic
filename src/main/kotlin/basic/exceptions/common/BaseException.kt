package com.lucas.basic.exceptions.common

/**
 * BaseException.kt: 공통 예외 클래스
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 23. 오전 1:50
 * @description: 공통 예외 클래스 - 커스텀 예외들의 부모 예외 클래스
 * - 특정한 응답 구조를 사용해야 하는 예외들의 경우 해당 클래스를 상속 받아, 별도의 예외 클래스를 생성하여 사용.
 * - Spring 에서 공통으로 사용하려면, 별도의 Filter, GlobalExceptionHandler 등을 사용하여 JSON 형태로 변환하여 사용해야 한다.
 *
 * - 상속을 허용하기 위해 open 키워드를 사용하여 상속 가능하도록 설정. (기본적으로 모든 Kotlin 클래스는 final 이다.)
 * - 또한 명시적으로 message 를 설정할 수 있도록 override -> Runtime 으로 전달 및 명시적 주입(속성처럼 다루기 위해 (Constructor 접근)), this.message 로 접근 위해서.
 */
open class BaseException (
    override val message: String,
    val code: String = "500", // 기본적으로 500 으로 설정
    override val cause: Throwable? = null, // Throwable 을 상속받는 모든 예외를 처리하기 위해
//    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, // Spring 에서 사용
) : RuntimeException(message)