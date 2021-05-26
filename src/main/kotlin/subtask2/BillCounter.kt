package subtask2

class BillCounter {

    // TODO: Complete the following function
    // The output could be "Bon Appetit" or the string with number(e.g "10")
    fun calculateFairlySplit(bill: IntArray, k: Int, b: Int): String {
        var halfPrice: Float = 0.0f

        for (i in 0 .. bill.size-1)
        {
            if(i != k) {
                halfPrice += bill[i]/2.0F
            }
        }
        return if((halfPrice - b).toInt() == 0) {
            "Bon Appetit"
        } else {
            (b - halfPrice).toInt().toString()
        }
    }
}
