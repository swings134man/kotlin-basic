package com.lucas.basic.clazz.sealeds

/**
 * SealedClazz.kt: Kotlin Sealed Class 사용 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 29. 오전 12:40
 * @description:
 */


// Sealed Class 를 사용하지 않는다면 ? - 아래처럼 interface 를 각각 구현 할 수 있음 -> 구현체가 이곳저곳으로 퍼져나갈 수 있음.
//interface Error
//class FileError(val fileName: String): Error
//class DbError(val dbmsType: DbmsType): Error

// Sealed Class 사용 - 내부에다가 만들거나({} 안에) 같은 파일안에 interface + 구현체 Class 를 넣어줘도 된다.
sealed class Error
class FileError(val fileName: String): Error() // 파일 관련 에러
class DbError(val dbmsType: DbmsType): Error() // DB 관련 에러


enum class DbmsType {
    MYSQL, ORACLE, MONGODB, MARIA, SQLSERVER
}


// 이렇게 아래와 같이 사용되는곳이 많아진다면? -> 각 에러가 추가되거나, 변경될때 마다 interface 를 구현한 클래스를 모두 찾아서 수정해야한다.
// 아래와 같은 구조 -> 패턴매칭
fun getErrorMsg(error: Error) = when(error) {
    is FileError -> "File Error: ${error.fileName}"
    is DbError -> "DB Error: ${error.dbmsType}"
    // 추가 정의된게 빠졌다면 ? 추가된 내용이 없다라고 컴파일 오류 발생한다.
}
