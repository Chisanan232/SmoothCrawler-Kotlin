package org.smoothcrawler.crawler

import org.smoothcrawler.factory.BaseFactory

/**
 * Simple Crawler: The simplest implementation of web spider
 *
 * It implements features minimum of web spider should have.
 *
 * @property factory the data class which saves some factories for generating components instance web spider needs.
 */
class SimpleCrawler(factory: BaseFactory? = null) : BaseCrawler(factory) {

    /**
     * Just crawl data from website and so some processing on it.
     * @param url the target URL to crawl.
     * @return the final data.
     */
    fun run(url: String): Any {
        val parsedData = crawl(url = url)
        return handleData(parsedResponse = parsedData)
    }

    /**
     * This function is an overload function of previous one. It receives a collection object of target URLs.
     * @param url the target URL to crawl.
     * @return the final data.
     */
    fun run(url: List<String>): List<Any> {
        return url.map {
            val parsedData = crawl(url = it)
            handleData(parsedResponse = parsedData)
        }
    }

}