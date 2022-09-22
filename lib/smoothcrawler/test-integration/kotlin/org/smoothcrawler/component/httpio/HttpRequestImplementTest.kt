package org.smoothcrawler.component.httpio

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.smoothcrawler.RuntimeTestVar
import org.smoothcrawler.implement.component.httpio.HttpRequestImpl
import java.net.http.HttpResponse

class HttpRequestImplementTest {

    private lateinit var underTest: BaseHttpRequest

    @BeforeEach
    fun initial() {
        underTest = HttpRequestImpl()
    }

    @Test
    fun `test implementing HttpRequest*request`() {
        val response: HttpResponse<String> = underTest.request("${RuntimeTestVar.URL}${RuntimeTestVar.httpGetRequestParams()}") as HttpResponse<String>
        val responseChar: String = response.body()
        assertThat(responseChar).isNotEmpty
        MatcherAssert.assertThat(responseChar, CoreMatchers.containsString("\"stat\":\"OK\""))
        MatcherAssert.assertThat(responseChar, CoreMatchers.containsString("台積電           各日成交資訊"))
        MatcherAssert.assertThat(responseChar, CoreMatchers.containsString("[\"111/06/01\",\"32,970,903\",\"18,171,598,472\",\"550.00\",\"555.00\",\"548.00\",\"549.00\",\"-11.00\",\"33,456\"]"))
    }

}