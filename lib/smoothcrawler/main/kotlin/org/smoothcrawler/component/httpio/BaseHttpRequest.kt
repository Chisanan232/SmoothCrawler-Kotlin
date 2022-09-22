package org.smoothcrawler.component.httpio

/**
 * HTTP request
 *
 * This class targets to handle anything about sending HTTP request.
 */
interface BaseHttpRequest {

    /**
     * The core function for sending HTTP request.
     *
     * Note:
     * It should consider that the data type could be designed as a generic (it means that it would be very varia) or
     * should be set as a specific class, for some reasons it would be better:
     * 1. You could be easy to predict the behavior how it works
     * 2. It could be more clear and verify how the code works
     *
     * Possible resolve 1. Generic type
     * Possible resolve 2. Any
     * Possible resolve 3. Specific class. It could be a marker interface
     */
    fun request(url: String): Any

}