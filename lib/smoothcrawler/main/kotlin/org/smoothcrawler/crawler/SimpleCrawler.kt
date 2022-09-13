package org.smoothcrawler.crawler

import org.smoothcrawler.factory.BaseFactory

class SimpleCrawler(factory: BaseFactory) : BaseCrawler(factory) {

    fun <T> run(url: String): T {
        val parsedData = crawl<T>(url = url)
        return handleData(parsedResponse = parsedData)
    }

    fun <T> run(url: List<String>): List<T> {
        return url.map {
            val parsedData: T = crawl(url = it)
            handleData(parsedResponse = parsedData)
        }
    }

}