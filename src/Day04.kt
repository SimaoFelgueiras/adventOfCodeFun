import kotlin.math.pow

fun main() {
    var counter = 0
    fun cardPoints(card: String, index: Int): Int {
        val numbers = card.split(":")[1]
        val (winNumbers, gameNumbers) = numbers.split("|")
        val winDigits = Regex("[0-9]+").findAll(winNumbers)
            .map(MatchResult::value)
            .toSet()

        val gameDigits = Regex("[0-9]+").findAll(gameNumbers)
            .map(MatchResult::value)
            .toSet()

        val points = gameDigits.intersect(winDigits).size

        return if (points > 0) {
            (2.0).pow(points - 1).toInt()
        } else 0
    }

    fun cardScratches(card: String, index: Int): Int {
        val numbers = card.split(":")[1]
        val (winNumbers, gameNumbers) = numbers.split("|")
        val winDigits = Regex("[0-9]+").findAll(winNumbers)
            .map(MatchResult::value)
            .toSet()

        val gameDigits = Regex("[0-9]+").findAll(gameNumbers)
            .map(MatchResult::value)
            .toSet()

        return gameDigits.intersect(winDigits).size
    }

    fun recursiveSolution(input: List<String>, initial: Int): Int {
        counter++
        val result = cardScratches(input[initial], initial)
        if (result != 0 && (initial + result) <= input.size) {
            for (i in (initial + 1)..(initial + result)) {
                recursiveSolution(input, i)
            }
        }
        return result
    }


    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEachIndexed { index, value ->
            sum += cardPoints(value, index)
        }
        return sum
    }

    fun part2Recursive(input: List<String>): Int {
        input.forEachIndexed { index, _ ->
           recursiveSolution(input, index)
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        return input.map { value ->
            val tokens = value.split(Regex(" +")).drop(2)
            val winningNumbers = tokens.takeWhile { it != "|" }.map { it.toInt() }
            val numbers = tokens.takeLastWhile { it != "|" }.map { it.toInt() }
            winningNumbers.count { it in numbers }
        }.reversed()
            .fold(emptyList<Int>()) { acc, winCount ->
                val count = 1 + (0..<winCount).sumOf { acc[it] }
                listOf(count) + acc
            }.sum()
    }


    val input = readInput("Day04")
    val input2 = readInput("Day04")
    part1(input).println()
    part2Recursive(input2).println()
    part2(input2).println()
}
