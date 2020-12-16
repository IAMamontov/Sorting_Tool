

fun generatePredicatablePassword(seed: Int): String {
    var randomPassword = ""
    // write your code here, Random is already imported for you
    val r = Random(seed)
    for (i in 0..9) {
        randomPassword += r.nextInt(33, 127).toChar()
    }
	return randomPassword
}