fun main() {

    fun parse(input: List<String>): Pair<Set<NumberLocation>, Set<Point2D>> {
        val numbers = mutableSetOf<NumberLocation>()
        val symbols = mutableSetOf<Point2D>()
        var workingNumber = NumberLocation()

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, character ->
                if (character.isDigit()) {
                    workingNumber.addNumber(character, Point2D(x, y))
                } else {
                    if (workingNumber.isNotEmpty()) {
                        numbers.add(workingNumber)
                        workingNumber = NumberLocation()
                    }

                    if (character.isSymbol()) {
                        symbols.add(Point2D(x, y))
                    }
                }
            }

        }

        if (workingNumber.isNotEmpty()) {
            numbers.add(workingNumber)
        }


        return numbers to symbols
    }


    fun parseTwo(input: List<String>): Pair<Set<NumberLocation>, Set<Point2D>> {
        val numbers = mutableSetOf<NumberLocation>()
        val symbols = mutableSetOf<Point2D>()
        var workingNumber = NumberLocation()

        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, character ->
                if (character.isDigit()) {
                    workingNumber.addNumber(character, Point2D(x, y))
                } else {
                    if (workingNumber.isNotEmpty()) {
                        numbers.add(workingNumber)
                        workingNumber = NumberLocation()
                    }

                    if (character.isAsterisk()) {
                        symbols.add(Point2D(x, y))
                    }
                }
            }

        }

        if (workingNumber.isNotEmpty()) {
            numbers.add(workingNumber)
        }


        return numbers to symbols
    }


    fun part1(input: List<String>): Int {
        val (numbers, symbols) = parse(input)

        return numbers.filter {
            it.isAdjacent(symbols)
        }.sumOf { it.toInt() }
    }

    fun part2(input: List<String>): Int {
        val (numbers, symbols) = parseTwo(input)
        var sum=0

        symbols.forEach { point->
            val closeNumbers=numbers.filter { it.isAdjacent(setOf( point)) }
            if(closeNumbers.size>1){
               val result= closeNumbers.map { it.toInt() }.fold(1) { total, next -> total * next }
                sum+=result
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day03_test")
    //val resultTest = part1(testInput)
    //println(resultTest)
    //check(resultTest == 4361)
    //check(part2(testInput) == 2286)


    val input = readInput("Day03")
    val input2 = readInput("Day03")
    part1(input).println()
    part2(input2).println()
}

private fun Char.isSymbol(): Boolean = isDigit().not() && this != '.'
private fun Char.isAsterisk(): Boolean = isDigit().not() && this == '*'
private data class Point2D(val x: Int, val y: Int) {
    fun neighbours(): Set<Point2D> =
        setOf(
            Point2D(x - 1, y - 1),
            Point2D(x, y - 1),
            Point2D(x + 1, y - 1),
            Point2D(x - 1, y),
            Point2D(x + 1, y),
            Point2D(x - 1, y + 1),
            Point2D(x, y + 1),
            Point2D(x + 1, y + 1),
        )
}

private class NumberLocation {
    private val numbers = mutableListOf<Char>()
    private val numbersLocation = mutableSetOf<Point2D>()

    fun addNumber(digit: Char, location: Point2D) {
        numbers.add(digit)
        numbersLocation.addAll(location.neighbours())
    }

    fun isNotEmpty() = numbers.isNotEmpty()

    fun isAdjacent(points: Set<Point2D>): Boolean = numbersLocation.intersect(points).isNotEmpty()

    fun toInt() = numbers.joinToString("").toInt()
}