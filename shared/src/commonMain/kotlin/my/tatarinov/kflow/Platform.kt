package my.tatarinov.kflow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform