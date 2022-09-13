package org.smoothcrawler.component.data


interface BaseHttpResponseParser {

    fun <T> parseContent(response: T): T

}
