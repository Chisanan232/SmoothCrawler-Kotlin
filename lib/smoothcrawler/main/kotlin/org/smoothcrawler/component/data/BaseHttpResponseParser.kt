package org.smoothcrawler.component.data

/**
 * Content ...
 */
interface BaseHttpResponseParser {

    /**
     * Content ...
     */
    fun <T> parseContent(response: T): T

}
