

fun generateTemperature(seed: Int) {
    val r = Random(seed)
    for (i in 0..6) {
        println(r.nextInt(20, 31))
    }
}
