package org.smoothcrawler.crawler

import org.smoothcrawler.factory.BaseFactory

abstract class BaseCrawler(private val factory: BaseFactory) {

    //TODO: Please consider about the generic data type. It should be more clear that what data it could receive and return
    open fun <T> crawl(url: String): T {
        val response: T = sendHttpRequest(url = url)
        return parseHttpResponse(response = response)
    }

    open fun <T> sendHttpRequest(url: String): T {
        return this.factory.httpRequest.request(url = url)
    }

    open fun <T> parseHttpResponse(response: T): T {
        return this.factory.httpResponseParser.parseContent(response = response)
    }

    open fun <T> handleData(parsedResponse: T): T {
        return this.factory.dataHandler.process(data = parsedResponse)
    }

}
