package org.smoothcrawler.factory

import org.smoothcrawler.component.data.BaseDataHandler
import org.smoothcrawler.component.data.BaseHttpResponseParser
import org.smoothcrawler.component.httpio.BaseHttpRequest

data class CrawlerFactory(
    override val httpRequest: BaseHttpRequest,
    override val httpResponseParser: BaseHttpResponseParser,
    override val dataHandler: BaseDataHandler,
): BaseFactory
