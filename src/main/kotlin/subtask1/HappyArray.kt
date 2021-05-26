package subtask1

class HappyArray {

    fun convertToHappy(sadArray: IntArray): IntArray {
        if (sadArray.size > 2) {
            var happyArrayList = ArrayList<Int>(0)
            var sadArrayList = ArrayList<Int>(sadArray.size)
            var iterationIdexcies = ArrayList<Int>(5)
            var happyArray: IntArray

            for (cn in 0 until sadArray.size) {
                sadArrayList.add(sadArray[cn])
            }

            for (evenAfterUpdate in 0 .. sadArray.size - 2) {

                if (!iterationIdexcies.isEmpty()) {
                    for (cn in 0 until  iterationIdexcies.size)
                    {
                        sadArrayList.removeAt(iterationIdexcies.get(cn) - cn)
                    }
                    iterationIdexcies.clear()
                }
                happyArrayList.clear()

                for (n in 1 .. sadArrayList.size - 2) {

                    val current = sadArrayList.get(n)
                    val next = sadArrayList.get(n + 1)
                    val prev = sadArrayList.get(n - 1)

                    if (next + prev >= current) {
                        happyArrayList.add(current)
                    } else {
                        iterationIdexcies.add(n)
                    }
                }
            }

            happyArray = IntArray(happyArrayList.size + 2)
            happyArray[0] = sadArray[0]
            happyArray[happyArray.size - 1] = sadArray[sadArray.size - 1]

            for (i in 0 until happyArrayList.size) {
                happyArray[i + 1] = happyArrayList.get(i)
            }

            return happyArray
        }
        return sadArray
    }
}
