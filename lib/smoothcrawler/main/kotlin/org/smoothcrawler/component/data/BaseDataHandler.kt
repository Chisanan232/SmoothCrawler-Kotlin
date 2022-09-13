package org.smoothcrawler.component.data


interface BaseDataHandler {

    fun <T> process(data: T): T

}
