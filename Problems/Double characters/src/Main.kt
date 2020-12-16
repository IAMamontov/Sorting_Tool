import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val string = scanner.nextLine()
    for (c in string) {
        print(c.toString().repeat(2))
    }
}