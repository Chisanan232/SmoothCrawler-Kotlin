package org.smoothcrawler.factory

import org.smoothcrawler.component.data.BaseDataHandler
import org.smoothcrawler.component.data.BaseHttpResponseParser
import org.smoothcrawler.component.httpio.BaseHttpRequest

/**
 * A data class for saving some factories web spider needs
 */
interface BaseFactory {
    val httpRequest: BaseHttpRequest
    val httpResponseParser: BaseHttpResponseParser
    val dataHandler: BaseDataHandler
}
