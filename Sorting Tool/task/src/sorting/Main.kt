package sorting

import java.io.File
import java.util.*
enum class DataType {LONG, LINE, WORD}
enum class SortingType {NATURAL, BY_COUNT}
enum class InputType {CONSOLE, FILE}
enum class OutputType {CONSOLE, FILE}
fun outputMethod(text: String, outputType: OutputType, fileName: String = "") {
    if (outputType == OutputType.CONSOLE) {
        print(text)
    } else {

        if (File(fileName).exists()) {
            File(fileName).appendText(text)
        } else {
            File(fileName).writeText(text)
        }
    }
}
fun main(args: Array<String>) {
    var dataType = DataType.WORD
    var inputType = InputType.CONSOLE
    var outputType = OutputType.CONSOLE
    var sortingType = SortingType.NATURAL
    var typeWord = ""
    val total: Int
    val scanner = Scanner(System.`in`)
    var inputFile = ""
    var outputFile = ""
    var values = emptyArray<String>()
    if (args.isNotEmpty()) {
        var i = 0
        while (i < args.size) {
            when (args[i]) {
                "-dataType" -> {
                    if (i < args.lastIndex) {
                        when (args[i + 1]) {
                            "long" -> {
                                dataType = DataType.LONG
                                i += 1
                            }
                            "line" -> {
                                dataType = DataType.LINE
                                i += 1
                            }
                            "word" -> {
                                dataType = DataType.WORD
                                i += 1
                            }
                            else -> {
                                println("No sorting type defined!")
                            }
                        }
                    } else { println("No sorting type defined!") }
                }
                "-sortingType" -> {
                    if (i < args.lastIndex) {
                        when (args[i + 1]) {
                            "natural" -> {
                                sortingType = SortingType.NATURAL
                                i += 1
                            }
                            "byCount" -> {
                                sortingType = SortingType.BY_COUNT
                                i += 1
                            }
                            else -> {
                                println("No data type defined!")
                            }
                        }
                    } else { println("No data type defined!") }

                }
                "-inputFile" -> {
                    if (i < args.lastIndex) {
                        inputFile = args[i + 1]
                        inputType = InputType.FILE
                        i += 1
                    }
                }
                "-outputFile" -> {
                    if (i < args.lastIndex) {
                        outputFile = args[i + 1]
                        outputType = OutputType.FILE
                        i += 1
                    }
                }
                else -> {
                    println("\"${args[i]}\" isn not a valid parameter. It will be skipped.")
                }
            }
            i += 1
        }
    }
    when (dataType) {
        DataType.LONG -> {
            typeWord = "number"
            if (inputType == InputType.CONSOLE) {
                while (scanner.hasNext()) {
                    val next = scanner.next()
                    try {
                        values += next.toInt().toString()
                    } catch (e: NumberFormatException) {
                        println("\"${next}\" is not a long. It will be skipped.")
                    }
                }
            } else {
                File(inputFile).forEachLine {
                    it.split("""\s""".toPattern()).forEach {
                        if (it.isNotBlank()) {
                            try {
                                values += it.toInt().toString()
                            } catch (e: NumberFormatException) {
                                println("\"${it}\" is not a long. It will be skipped.")
                            }
                        }
                    }
                }
            }

        }
        DataType.LINE -> {
            typeWord = "line"
            if (inputType == InputType.CONSOLE) {
                while (scanner.hasNextLine()) {
                    values += scanner.nextLine()
                }
            } else {
                File(inputFile).forEachLine { values += it }
            }
        }
        DataType.WORD -> {
            typeWord = "word"
            if (inputType == InputType.CONSOLE) {
                scanner.useDelimiter("""\s""".toPattern())
                while (scanner.hasNext()) {
                    val word = scanner.next()
                    if (word.isNotBlank()) {
                        values += word
                    }

                }
            } else {
                File(inputFile).forEachLine {
                    it.split("""\s""".toPattern()).forEach {
                        if (it.isNotBlank()) {
                            values += it
                        }
                    }
                }
            }
        }
    }
    total = values.size
    //println("Total ${typeWord}s: $total.")
    outputMethod("Total ${typeWord}s: $total.\n", outputType, outputFile)
    when (sortingType) {
        SortingType.NATURAL -> {
            //print("Sorted data: ")
            outputMethod("Sorted data: ", outputType, outputFile)
            when (dataType) {
                DataType.LONG -> {
                    val result = values.map { it.toInt() }
                    //result.sorted().forEach { e -> print("$e ") }
                    result.sorted().forEach {
                        e -> outputMethod("$e ", outputType, outputFile)
                    }
                }
                DataType.LINE -> {
                    print("\n")
                    //values.sorted().forEach { e -> println("$e ") }
                    values.sorted().forEach {
                        e -> outputMethod("$e \n", outputType, outputFile)
                    }
                }
                DataType.WORD -> {
                    //values.sorted().forEach { e -> print("$e ") }
                    values.sorted().forEach {
                        e -> outputMethod("$e ", outputType, outputFile)
                    }
                }
            }
        }
        SortingType.BY_COUNT -> {
            val sortFreq = values.groupingBy { it }.eachCount()
            val freqList = sortFreq.values.toList().distinct().sorted()
            if (dataType == DataType.LONG) {
                val longList = sortFreq.keys.toList().map { it.toInt() }
                freqList.forEach {
                    i -> longList.filter { sortFreq[it.toString()] == i }.sorted().forEach {
                        outputMethod("$it: $i time(s), ${100 * i / total}%\n", outputType, outputFile)
                    }
                }
            } else {
                freqList.forEach {
                    i -> sortFreq.filterValues { it == i }.toSortedMap().forEach {
                        (s, i) -> outputMethod("$s: $i time(s), ${100 * i / total}%\n", outputType, outputFile)
                    }
                }
            }
        }
    }
}

