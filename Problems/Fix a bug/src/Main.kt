class Article(val name: String, val pages: Int, val author: String) {
    operator fun component1(): String = name
    operator fun component2(): Int = pages
    operator fun component3(): String = author
}

fun getArticleByName(articles: Array<Article>, name: String): Article? {
    var index = -1
    for ((title, _, _) in articles) {
        index += 1
        if (title == name) return articles[index]
    }
    return null
}