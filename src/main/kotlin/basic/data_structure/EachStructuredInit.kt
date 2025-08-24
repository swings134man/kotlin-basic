package com.lucas.basic.data_structure

/**
 * EachStructuredInit.kt: 각 자료구조의 초기화 방법
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 8. 24. 오후 5:01
 * @description:
 */
fun main() {

    // -------------------------- 초기값 존재할 경우 --------------------------
    // Array
    var arr = Array<Int>(5) { 0 } // 크기 5, 초기값 0
    var arr2 = arrayOfNulls<Int>(5) // 초기값 없는 array: 크기 5, 초기값 null

    // List 초기값 없는 (Mutable)
    var list = mutableListOf<Int>() // 빈 리스트
    var list2 = mutableListOf<String>()

    // ArrayList: 초기값 없는
    var arrayList = arrayListOf<Int>() // 빈 리스트
    var arrayList2 = arrayListOf<String>()


    // Map 초기값 없는 (Mutable)
    var map = mutableMapOf<String, Int>() // 빈 맵
    var map2 = mutableMapOf<Int, String>()

    // Set 초기값 없는 (Mutable)
    var set = mutableSetOf<Int>() // 빈 셋
    var set2 = mutableSetOf<String>()



    // -------------------------- 초기값 존재할 경우 --------------------------

    var arrInit = arrayOf(1, 2, 3, 4, 5) // Array 초기값 존재

    var listInit = mutableListOf(1, 2, 3, 4, 5) // List 초기값 존재
    var listInit2: MutableList<String> = mutableListOf("one", "two", "three") // List 초기값 존재 (Immutable)

    var arrayListInit = arrayListOf(1, 2, 3, 4, 5) // ArrayList : 기본적으로 Mutable
    // 2차원 ArrayList
    var arrayList2DInit = arrayListOf(arrayListOf(1, 2, 3), arrayListOf(4, 5, 6), arrayListOf(7, 8, 9))
    arrayList2DInit.get(0).get(0) // 1
    arrayList2DInit[0][0] // 1

    var mapInit = mutableMapOf("one" to 1, "two" to 2, "three" to 3) // Map 초기값 존재
    var setInit = mutableSetOf(1, 2, 3, 4, 5) // Set 초기값 존재


}