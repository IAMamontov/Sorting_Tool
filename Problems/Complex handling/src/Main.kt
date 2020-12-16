try {
    suspiciousFunction(data)

}
catch (e: IOException) {
    println("The IOException occurred")
    // exception handler
}

catch (e: Exception) {
    println(e.message)
}
finally {
    // code is always executed
    println("Handling completed successfully!")
}


