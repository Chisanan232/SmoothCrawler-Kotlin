package org.smoothcrawler.component.httpio

interface BaseHttpRequest {

    fun <T> request(url: String): T

}