
val advice = try {
   pepTalk(name)
    // code that may throw an exception
}
catch (e: Exception) {
    "Don't lose the towel, nameless one."
    // exception handler
}
finally {
    // code is always executed
    println("Good luck!")
}
println(advice)

