

fun throwD6(): Int {
    // write your code here, Random is already imported for you
	val r = Random.Default
	val numberOnTheDice = r.nextInt(1, 7)
	return numberOnTheDice
}