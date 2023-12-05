fun main() {

    fun extractDigitsFromLine(elfLine:String):String{
        val numbers=elfLine.filter{ it.isDigit()}.map { it }
        return if(numbers.size==1){
            numbers.first().toString() + numbers.first().toString()
        }else {
            numbers.first().toString() + numbers.last()
        }

    }

    fun subStringNumberThreeDigit(value:String):String {
        return when(value){
            "one" -> "1"
            "two" -> "2"
            "six" -> "6"
            else -> "0"
        }
    }

    fun subStringNumberFourDigit(value:String):String {
        return when(value){
            "four" -> "4"
            "five" -> "5"
            "nine" -> "9"
            else -> "0"
        }
    }

    fun subStringNumberFiveDigit(value:String):String {
        return when(value){
            "three" -> "3"
            "seven" -> "7"
            "eight" -> "8"
            else -> "0"
        }
    }


    fun extractDigitsAndStringNumbersFromLine(elfLine:String):Int{
        val digitList= mutableListOf<String>()
        elfLine.forEachIndexed { index, it ->
            if(it.isDigit()){
                digitList.add(it.toString())
            }else {
                if((index + 3) <= elfLine.length){
                    digitList.add(subStringNumberThreeDigit(elfLine.substring(index, index+3)))
                }
                if((index + 4) <= elfLine.length){
                    digitList.add(subStringNumberFourDigit(elfLine.substring(index, index+4)))
                }
                if((index + 5) <= elfLine.length){
                    digitList.add(subStringNumberFiveDigit(elfLine.substring(index, index+5)))
                }
            }
        }
        digitList.println()
        digitList.removeAll { it=="0" }
        digitList.println()
        return if(digitList.size==1){
            (digitList.first() + digitList.first()).toInt()
        }else {
            (digitList.first() + digitList.last().toInt()).toInt()
        }
    }

    fun part1(input: List<String>): Int {
        var sum=0
        input.forEach { sum += extractDigitsFromLine(it).toInt()  }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum=0
        input.forEach{
            sum +=  extractDigitsAndStringNumbersFromLine(it)
        }
        return sum
    }


    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01_test")
    //part1(input).println()
    part2(input).println()
}