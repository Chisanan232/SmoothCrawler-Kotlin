package org.smoothcrawler.component.data

/**
 * Content ...
 */
interface BaseDataHandler {

    /**
     * Content ...
     */
    fun <T> process(data: T): T

}
