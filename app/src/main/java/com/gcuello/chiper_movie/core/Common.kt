package com.gcuello.chiper_movie.core

object Common {

    var PAGE = 0
    var PAGE_TOP_RATE = 0
    const val POST_PER_PAGE = 50
    val PARAMS: MutableMap<String, String> = mutableMapOf()

    private var _baseUrl: String? = null
    val BASE_URL get() = _baseUrl!!

    private var _apiKey: String? = null
    val API_KEY get() = _apiKey!!

    fun attachUrls(vararg args: String) {
        _baseUrl = args[0]
        _apiKey = args[1]
    }

    fun setParams() {
        PARAMS.clear()
        PARAMS["api_key"] = API_KEY
        PARAMS["language"] = "en-US"
    }
}