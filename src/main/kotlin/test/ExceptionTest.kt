package com.lucas.test

import com.lucas.basic.exceptions.common.BaseException
import com.lucas.basic.exceptions.common.BusinessException

/**
 * ExceptionTest.kt: Custom Exception 클래스를 테스트 하기 위한 테스트 클래스
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 23. 오전 2:01
 * @see: com.lucas.basic.exceptions.common.BaseException, com.lucas.basic.exceptions.common.BusinessException
 */
fun main() {

//    throw BusinessException("Test error")

    try {
        throw BaseException("Test error") // default Code 500
//        throw BusinessException("Test error") // default Code 400
    } catch (e: BaseException) {
        println(e.message)
        println(e.code)
        println(e.cause)
    }
}