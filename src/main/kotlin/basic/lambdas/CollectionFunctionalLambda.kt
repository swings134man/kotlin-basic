package com.lucas.basic.lambdas

/**
 * CollectionFunctionalLambda.kt: 컬렉션 함수형 API Lambda 예제
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 5. 29. 오후 10:26
 * @description:Java Stream 을 사용하지 않고 편하게 사용 가능.
 *
 * - filter, map : 가장 기초가 되는 함수
 *      - filter: 데이터를 필터링
 *      - map: 데이터를 다른 데이터로 변환(매핑)
 *
 * - all: 모두 조건에 맞는가?
 * - any: 하나라도 조건에 맞는가?
 * - count: 조건에 맞는 데이터의 개수
 * - find: 조건에 맞는 첫번째 데이터 - 없으면 null
 *
 * - groupBy: 데이터를 그룹화(SQL 의 GROUP BY 와 유사)
 *
 * - flatten: 중첩 컬렉션(List<List<T>> 와 같은)을 단일 컬렉션 (List<T>)로 평탄화
 * - flatMap: 데이터를 평탄화 -> 여러 개의 데이터를 하나의 데이터로 변환
 *      - map + flatten 의 조합
 *      - map을 사용하여 각 요소를 변환한 후, flatten을 사용하여 중첩된 컬렉션을 평탄화
 *
 * - sequence: 컬렉션을 시퀀스로 변환하여 지연 연산을 수행 - asSequence(): Iterable 이 존재할 경우 사용, sequenceOf(): Iterable 이 없는 경우 사용
 *      - 자바의 stream 과 같은 역할을 함
 *      - 지연 연산: 컬렉션의 요소를 필요할 때만 계산 -> 메모리 사용량을 줄이고 성능을 향상시킬 수 있음
 *          - 전체요소를 한번에 계산하지 않고, 필요한 요소만 계산 후 반환함.
 *      - 대량 컬렉션에서 필터링, 찾기 연산 하는 경우 유용함
 *          - sequence 가 아닌 경우 무조건 모든 결과를 계산
 *          - sequence 는 조건에 맞는경우, 결과가 필요한 경우에 한해서 계산을 진행
 *      - 일반적인 iterable 은 모든 아이템이 각 스텝별로 실행 -> 시퀀스는 요소 하나당 각 스텝 실행하는 방식
 */
data class Person(val name: String, var age: Int)

fun main() {
    val persons = listOf(
        Person("Alice", 30),
        Person("Bob", 25),
        Person("Charlie", 35),
        Person("DeadPool", 40),
        Person("Elk", 40),
    )

    // 나이가 35 이상인 사람만 필터링
    println(persons.filter { it.age >= 35 })

    println(persons
        .filter { it.age >= 35 }
        .map { "${it.name} : ${it.age}" } // 필터링 후 이름과 나이만 추출하여 문자열(String)로 변환
    )

    println(persons.count{it.age >= 35}) // 2
    println(persons.any { it.age >= 35 }) // 하나라도 조건에 맞는가? true
    println(persons.all { it.age > 35 }) // 모두 조건에 맞는가? false
    println(persons.find { it.age >= 35 }) // 조건에 맞는 첫번째 데이터 - Charlie

    // ----
    // 그룹화 - 나이로 그룹화
    val groups = persons.groupBy { it.age }
    println(groups)
    println("Groups : ${groups[40]}") // age 그룹이 40 인 객체 출력


    // 시퀀스 vs Iterable
    // iterable - 전체 요소가 - filter-map-foreach 순서
    listOf(1, 2, 3, 4)
        .filter { println("[ITEM]: $it"); it % 2 == 0 }
        .map { println("[EVEN ITEM]: $it"); it * 11 }
        .forEach { println("[MAPPED ITEM]: $it") }

    // sequence - 각 요소가 - filter-map-foreach 순서 - 각 조건 만족할때만 다음요소 (지연연산: 불필요한것을 계산하지 않음)
    listOf(1, 2, 3, 4).asSequence()
        .filter { println("[ITEM]: $it"); it % 2 == 0 }
        .map { println("[EVEN ITEM]: $it"); it * 11 }
        .forEach { println("[MAPPED ITEM]: $it") }

    // iterable 이 없는 경우
     sequenceOf(1, 2, 3, 4)
        .filter { println("[ITEM]: $it"); it % 2 == 0 }
        .map { println("[EVEN ITEM]: $it"); it * 11 }
        .forEach { println("[MAPPED ITEM]: $it") }
}