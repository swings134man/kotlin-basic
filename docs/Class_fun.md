# Class VS fun

### 클래스와 함수의 차이점에 대해서

### Class
- 상태(state)를 가져야 한다. -> class User(val name: String, val age: Int)
- 여러 함수가 하나의 책임을 가져야 할때 -> UserService.findByUser()
- `객체지향`적인 코드를 작성할때
- 확장성을 고려해야 할때 
- 외부 의존성 주입(`DI`) 가 필요한 경우

---
### Fun
- 상태(state)를 가지지 않는다. -> fun findByUser(name: String, age: Int)
- 유틸성 함수 -> toString(), toSnakeCase()
- 확장 함수 -> String.toSnakeCase()
  - 확장 함수의 경우 Class 밖, 즉 클래스 내부가 아닌 파일에 정의 되어있음
    - 변수의 동적 타입으로 어떤걸 사용할지 정의되지 않음. -> `변수의 정적 타입으로 결정됨`
    - `오버라이드` 되지 않는다 라는 결론이 남
- 함수형 처리 -> val result = list.map(::double)
- 테스트/스크립트 용도 : 작은 단위의 로직, 학습용 코드 등
- 다만 Singleton 패턴을 사용해야 할 경우에는 `object` 를 사용한다. -> 전역 유틸 객체가 되는것.
--- 
### 결론 

- Kotlin은 OOP + 함수형 혼합 스타일 유연하게 사용하는것이 강점
- 불필요한 Class 를 만들지 않음 ! - `진짜 상태가 필요한 것`만 Class 로 사용
- 나머지는 `fun, object, file-level function` 으로 최대한 간결하게 사용
<br><br>
- <b>로직은 함수로 분리</b>
- <b>상태와 책임은 Class 로 묶는 구조</b>
---

### Sample Codes!

- Class - Entity data class
```kotlin
// Class - Entity (UserEntity)
package com.example.domain.user

// equals/hashCode/toString 자동 생성 Class(data class)
data class UserEntity(
    val id: Long,
    val name: String,
    val age: Int
)
```

- fun - 유틸성 파일
```kotlin
// fun - 유틸성 파일(이름): StringUtils
package com.example.utils

// 확장함수 : 기존의 String 클래스에 기능을 추가
fun String.isEmail(): Boolean {
    // 이메일 정규식 체크
    return Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matches(this)
}

// 추가 기능
fun String.toSnakeCase(): String {
    // 스네이크 케이스 변환
    return this.split(" ").joinToString("_").lowercase()
}
```

- Class + fun 사용 샘플 예시
```kotlin
import com.example.model.User
import com.example.util.isEmail
import com.example.util.capitalizeFirst

fun main() {
    // class
    val user = User(1, "jang", "jang@example.com")

    // fun(extension function)
    if (user.email.isEmail()) {
        println("✅ 유효한 이메일입니다")
    }

    println("이름: ${user.name.capitalizeFirst()}")
}
```