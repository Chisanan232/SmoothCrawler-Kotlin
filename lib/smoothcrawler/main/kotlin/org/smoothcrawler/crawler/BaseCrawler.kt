package org.smoothcrawler.crawler

import org.smoothcrawler.factory.BaseFactory

/**
 * Define some basic attributes of web spider.
 * It also provides some template methods to let subclass extends it or overrides it.
 *
 * @property factory the data class which saves some factories for generating components instance web spider needs.
 */
abstract class BaseCrawler(private val factory: BaseFactory? = null) {

    /**
     * A template method for an entire process of crawling data from website and do some processing on it.
     * @param url the target URL to crawl.
     * @return the running result which has been handled.
     */
    //TODO: Please consider about the generic data type. It should be more clear that what data it could receive and return
    open fun crawl(url: String): Any {
        val response: Any = sendHttpRequest(url = url)
        return parseHttpResponse(response = response)
    }

    /**
     * A template method for sending HTTP request to target URL.
     * @param url the target URL to crawl.
     * @return the HTTP response which is running result of crawling.
     */
    open fun sendHttpRequest(url: String): Any {
        return this.factory!!.httpRequest.request(url = url)
    }

    /**
     * A template method for parsing HTTP response. Just ONLY parse the data from HTML.
     * @param response the target URL to crawl.
     * @return the parsed data from HTTP response.
     */
    open fun parseHttpResponse(response: Any): Any {
        return this.factory!!.httpResponseParser.parseContent(response = response)
    }

    /**
     * A template method for run data processing of parsed result from HTTP response.
     * @param parsedResponse the data which has been parsed from HTTP response.
     * @return the final data.
     */
    open fun handleData(parsedResponse: Any): Any {
        return this.factory!!.dataHandler.process(data = parsedResponse)
    }

}
