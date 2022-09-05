package org.smoothcrawler.factory

import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.smoothcrawler.component.httpio.HttpRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class CrawlerFactoryTest {

    private lateinit var crawlerFactory: CrawlerFactory

    @MockK
    private lateinit var httpRequest: HttpRequest

    @BeforeEach
    fun setup() {
        httpRequest = HttpRequest()

        crawlerFactory = CrawlerFactory(
            httpRequest = httpRequest,
            httpResponseParser = mockk(name = "HttpResponseParser"),
            dataHandler = mockk(name = "DataHandler"),
        )
    }

    @Test
    fun testCrawlerFactory() {
        assertNotNull(crawlerFactory.httpRequest)
        assertNotNull(crawlerFactory.httpResponseParser)
        assertNotNull(crawlerFactory.dataHandler)
    }

}
