package org.smoothcrawler.component.data

import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.smoothcrawler.RuntimeTestVar
import org.smoothcrawler.component.httpio.BaseHttpRequest
import org.smoothcrawler.implement.component.data.HttpResponseParserImpl
import org.smoothcrawler.implement.component.httpio.HttpRequestImpl
import java.net.http.HttpResponse

class HttpResponseImplementTest {

    private lateinit var httpRequest: BaseHttpRequest

    private lateinit var underTest: BaseHttpResponseParser

    @BeforeEach
    fun initial() {
        httpRequest = HttpRequestImpl()
        underTest = HttpResponseParserImpl()
    }

    @Test
    fun `test implementing BaseHttpResponseParser*parseContent`() {
        val response: HttpResponse<String> = httpRequest.request("${RuntimeTestVar.URL}${RuntimeTestVar.httpGetRequestParams()}") as HttpResponse<String>
        val parsedDataFromHttpResponse: String = underTest.parseContent(response) as String
        Assertions.assertThat(parsedDataFromHttpResponse).isNotEmpty
        MatcherAssert.assertThat(parsedDataFromHttpResponse, CoreMatchers.containsString("\"stat\":\"OK\""))
        MatcherAssert.assertThat(parsedDataFromHttpResponse, CoreMatchers.containsString("台積電           各日成交資訊"))
        MatcherAssert.assertThat(parsedDataFromHttpResponse, CoreMatchers.containsString("[\"111/06/01\",\"32,970,903\",\"18,171,598,472\",\"550.00\",\"555.00\",\"548.00\",\"549.00\",\"-11.00\",\"33,456\"]"))
    }

}