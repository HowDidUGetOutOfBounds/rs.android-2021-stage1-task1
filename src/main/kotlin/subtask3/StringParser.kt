package subtask3

class StringParser {

    fun getResult(inputString: String): Array<String> {
        var openings = arrayListOf<String>("[", "<", "(")
        var closings = arrayListOf<String>("]", ">", ")")

        openings = findArrayInputs(inputString)
        closings = fromOpenings(openings)

        var subStrIdxes = ArrayList<Pair<Int, Int>>()
        var naturalOrderOpenings = ArrayList<Int>()
        var naturalOrderClosings = ArrayList<Int>()
        var subStringsResults = ArrayList<String>()

        for (elements in 0 until openings.size) {
            var cn: Int = 0;
            var lastPosFound: Int = 0;
            subStrIdxes.clear()
            naturalOrderClosings.clear()
            naturalOrderOpenings.clear()

            for (i in 0 until inputString.length) {
                if (lastPosFound < inputString.length) {
                    if (inputString.indexOf(openings[elements], lastPosFound) == -1) {
                        break
                    }
                    lastPosFound = inputString.indexOf(openings[elements], lastPosFound) + 1
                    naturalOrderOpenings.add(lastPosFound - 1)
                    cn++
                } else {
                    break
                }
            }

            lastPosFound = 0

            for (i in 0 until inputString.length) {
                if (lastPosFound < inputString.length) {
                    if (inputString.indexOf(closings[elements], lastPosFound) == -1) {
                        break
                    }
                    lastPosFound = (inputString.indexOf(closings[elements], lastPosFound) + 1)
                    naturalOrderClosings.add(lastPosFound - 1)
                } else {
                    break
                }
            }

            subStrIdxes = findNext(naturalOrderOpenings, naturalOrderClosings)

//        for (i in 0 until subStrIdxes.size) {
//            subStrIdxes[i] = Pair(subStrIdxes[i].first, naturalOrderClosings.get(cn - i - 1))
//        }

            for (i in 0 until subStrIdxes.size) {
                subStringsResults.add(
                    inputString.substring(
                        subStrIdxes[i].first + 1,
                        subStrIdxes[i].second
                    )
                )
            }
        }

        val resultArray: Array<String> = subStringsResults.toTypedArray()

        return resultArray
    }


    fun findArrayInputs(inputString: String): ArrayList<String> {
        var a = inputString.indexOf("<")
        var b = inputString.indexOf("(")
        var c = inputString.indexOf("[")
        var res = ArrayList<String>()
        var myMap = mutableMapOf<Int, String>(a to "<", b to "(", c to "[")
        var data = arrayListOf<Int>(a, b, c)

        do {
            res.add(myMap.get(data.min())!!)
            data.remove(data.min()!!)
        } while (data.size != 0)

        return res

    }

    fun fromOpenings(openings: ArrayList<String>): ArrayList<String> {
        var res = ArrayList<String>()
        openings.forEach {
            if (it == "<")
                res.add(">")
            else if (it == "(")
                res.add(")")
            else if (it == "[") {
                res.add("]")
            }
        }
        return res
    }

    fun findNext(
        naturalOrderOpenings: ArrayList<Int>,
        naturalOrderClosings: ArrayList<Int>
    ): ArrayList<Pair<Int, Int>> {
        var result = ArrayList<Pair<Int, Int>>()
        var iteraionBufOp: ArrayList<Int>
        var iterationBufCl: ArrayList<Int>
        var toStartWith: ArrayList<Int>

        if (naturalOrderOpenings.size >= 2) {
            for (i in 0..naturalOrderOpenings.size - 2) {

                iteraionBufOp = ArrayList(naturalOrderOpenings)
                iterationBufCl = ArrayList(naturalOrderClosings)
                toStartWith = ArrayList(naturalOrderOpenings)
                toStartWith.sort()

                var startSearch = toStartWith[0]

                toStartWith.removeAt(0)

                var ex = false
                var cn = 0


                do {
                    var minOp = findMin(iteraionBufOp) ?: Int.MAX_VALUE
                    var minCl = findMin(iterationBufCl)


                    if (minOp < minCl!!) {
                        cn++
                        iteraionBufOp.remove(minOp)
                    } else {
                        cn--
                        if (cn == 0) {
                            result.add(Pair(startSearch, minCl) as Pair<Int, Int>)
                            naturalOrderOpenings.remove(startSearch)
                            naturalOrderClosings.remove(minCl)
//                            iteraionBufOp.remove(minOp)
//                            iterationBufCl.remove(minCl)
                            ex = true
                        } else {
                            iterationBufCl.remove(minCl)
                        }
                    }

                } while (!ex)
            }
        }

        result.add(Pair(naturalOrderOpenings[0], naturalOrderClosings[0]))

        return result

    }

    fun findMin(list: List<Int>): Int? {
        return list.min()
    }
}
