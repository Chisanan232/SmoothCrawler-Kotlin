package org.smoothcrawler.factory

import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class CrawlerFactoryTest {

    private lateinit var crawlerFactory: CrawlerFactory

    @BeforeEach
    fun setup() {
        crawlerFactory = CrawlerFactory(
            httpRequest = mockk(name = "HttpRequest"),
            httpResponseParser = mockk(name = "HttpResponseParser"),
            dataHandler = mockk(name = "DataHandler"),
        )
    }

    @Test
    fun testHttpRequest() {
        assertNotNull(crawlerFactory.httpRequest)
    }

    @Test
    fun testHttpResponseParser() {
        assertNotNull(crawlerFactory.httpResponseParser)
    }

    @Test
    fun testDataHandler() {
        assertNotNull(crawlerFactory.dataHandler)
    }

}
