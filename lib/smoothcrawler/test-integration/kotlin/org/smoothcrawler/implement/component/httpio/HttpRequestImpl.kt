package org.smoothcrawler.implement.component.httpio

import org.smoothcrawler.component.httpio.BaseHttpRequest
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpRequestImpl: BaseHttpRequest {

    override fun request(url: String): HttpResponse<String> {
        val testingTargetUri = generateURI(url)
        val httpClient = buildHttpClient()
        val httpRequest = initialHttpRequest(testingTargetUri)
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    }

    private fun generateURI(url: String): URI {
        return URI.create(url)
    }

    private fun buildHttpClient(): HttpClient {
        return HttpClient.newBuilder().build()
    }

    private fun initialHttpRequest(uri: URI): HttpRequest {
        return HttpRequest.newBuilder().uri(uri).build()
    }

}