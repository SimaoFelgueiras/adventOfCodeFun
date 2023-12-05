import java.lang.Exception

fun main() {
    fun mapSymbol(input: List<String>, word: String): List<List<String>> {
        val gameUnits = mutableListOf<List<String>>()
        val unit = mutableListOf<String>()

        input.forEach {
            if (word.contains("$it;")) {
                unit.add(it)
                gameUnits.add(unit.toList())
                unit.clear()
            } else {
                unit.add(it)
            }
        }
        gameUnits.add(unit)
        return gameUnits
    }

    fun part1(input: List<String>, red: Int, green: Int, blue: Int): Int {
        var sum = 0
        var counter=0
        var isValidId = true
        input.forEach { value ->
            val gameNumber = value.split(":")[0].split(" ")[1].toIntOrNull() ?: 0
            val subCubes = value.substringAfter(":")
            val cubes = subCubes.split(";").joinToString { it }.split(",")
            val setOfCubes = mapSymbol(cubes, subCubes)

            setOfCubes.forEach {
                var sumOfLReds = 0
                var sumOfLGreens = 0
                var sumOfLBlues = 0
                it.forEach { hand ->
                    val split = hand.trim().split(" ")

                    when (split[1]) {
                        "green" -> {
                            val conversionToInt = split[0].toIntOrNull() ?: 0
                            sumOfLGreens += conversionToInt
                        }

                        "blue" -> {
                            val conversionToInt = split[0].toIntOrNull() ?: 0
                            sumOfLBlues += conversionToInt
                        }

                        "red" -> {
                            val conversionToInt = split[0].toIntOrNull() ?: 0
                            sumOfLReds += conversionToInt
                        }
                    }
                }
                if (sumOfLBlues > blue || sumOfLReds > red || sumOfLGreens > green) {
                    isValidId = false
                }
            }
            if(isValidId){
                sum += gameNumber
                counter++
            }
            isValidId=true
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    //12 red cubes, 13 green cubes, and 14 blue cubes?
    //part1(testInput, red = 12, green = 13, blue = 14).println()
// val testInput2 = readInput("Day01_2_test")
// check(sumOfAllNumbers(testInput2) == 142)

//    val input = readInput("Day02_")
    //  val input2 = readInput("Day02")
    //12 red cubes, 13 green cubes, and 14 blue cubes
    part1(testInput, red = 12, green = 13, blue = 14).println()
    println("================")
    //part2(input2).println()
}