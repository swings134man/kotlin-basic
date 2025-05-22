package com.lucas.basic.exceptions.common

/**
 * BusinessException.kt: 비지니스 예외 클래스 - BaseException 을 상속
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 23. 오전 2:00
 * @see: com.lucas.basic.exceptions.common.BaseException
 */
class BusinessException(message: String) : BaseException(message, "400")