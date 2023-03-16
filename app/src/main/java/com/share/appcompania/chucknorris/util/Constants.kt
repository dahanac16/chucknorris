package com.share.appcompania.chucknorris.util

object Constants {
    class Api {
        companion object {
            const val BASE_URL = "https://api.chucknorris.io/jokes/"
        }
    }
    class EndPoint {
        companion object {
            const val CATEGORIES = "categories"
            const val RANDOM_CATEGORIES = "random?category="
        }
    }
}