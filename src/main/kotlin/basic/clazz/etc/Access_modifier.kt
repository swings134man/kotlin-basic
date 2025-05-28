package com.lucas.basic.clazz.etc

/**
 * Access_modifier.kt: 클래스 내에서 접근제어자 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 29. 오전 2:03
 * @description: Entity 클래스와 같이 필드 데이터를 직접 수정하면 안될경우, backing field 와 같은 기능을 사용하여 수정하도록 함
 */

class AccountClass {
    var balance: Long = 0 //
        private set // 외부에서 직접 수정할 수 없도록 private set 으로 설정

    var accountName: String = ""
        get() = "계좌이름:$field" // 외부에서 읽을 수 있지만, 내부적으로는 backing field 를 사용하여 값을 반환

    // 외부에서 balance 값을 수정할 수 없지만, 내부적으로는 메소드를 통해 수정할 수 있다.
    fun increaseBalance(amount: Long) {
        this.balance += amount
    }

    // 아래처럼 조건을 걸어서 해야되는 경우나, 값 직접 수정하면 안될경우 사용함(@See Java Entity)
    fun decreaseBalance(amount: Long) {
        if (this.balance >= amount) {
            this.balance -= amount
        } else {
            println("잔액이 부족합니다.")
        }
    }
}

fun main() {
    val account = AccountClass()
//    account.balance = 1000 // 컴파일 에러: private set 으로 설정되어 있어 외부에서 수정할 수 없음
    account.accountName = "1번 계좌"
    println(account.accountName) // "계좌이름:" 출력

    // 잔액 증가
    account.increaseBalance(2000)
    account.decreaseBalance(1000)
    println("잔액: ${account.balance}") // 잔액: 1000 출력
}