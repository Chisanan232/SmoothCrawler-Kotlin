package org.smoothcrawler.component.httpio

/**
 * HTTP request
 *
 * This class targets to handle anything about sending HTTP request.
 */
interface BaseHttpRequest {

    /**
     * The core function for sending HTTP request.
     */
    fun <T> request(url: String): T

}