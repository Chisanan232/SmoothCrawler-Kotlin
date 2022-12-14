package org.smoothcrawler.crawler

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.smoothcrawler.factory.CrawlerFactory

class SimpleCrawlerTest {

    private lateinit var underTest: SimpleCrawler

    @MockK
    private lateinit var crawlerFactory: CrawlerFactory

    @BeforeEach
    fun setupCrawler() {
        crawlerFactory = CrawlerFactory(
            mockk("HttpRequest"),
            mockk("HttpResponseParser"),
            mockk("DataHandler"),
        )

        underTest = SimpleCrawler(crawlerFactory)
    }

    @Test
    fun testRunWithOneURL() {
        mockFactoryFunctions()

        underTest.run("test url")

        verifyFunctionRunningHistory(1)
    }

    @Test
    fun testRunWithManyURLs() {
        mockFactoryFunctions()

        val urls = listOf("test url 1", "test url 2", "test url 3")
        underTest.run(urls)

        verifyFunctionRunningHistory(urls.size)
    }

    private fun mockFactoryFunctions() {
        every {
            crawlerFactory.httpRequest.request(any())
        } returns Any()
        every {
            crawlerFactory.httpResponseParser.parseContent(any())
        } returns Any()
        every {
            crawlerFactory.dataHandler.process(any())
        } returns Any()
    }

    private fun verifyFunctionRunningHistory(exactlyTimes: Int) {
        verify (exactly = exactlyTimes) {
            crawlerFactory.httpRequest.request(any())
        }
        verify (exactly = exactlyTimes) {
            crawlerFactory.httpResponseParser.parseContent(any())
        }
        verify (exactly = exactlyTimes) {
            crawlerFactory.dataHandler.process(any())
        }
    }

}