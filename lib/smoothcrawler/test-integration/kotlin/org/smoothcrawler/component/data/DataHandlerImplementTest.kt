package org.smoothcrawler.component.data

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.json.JSONArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.smoothcrawler.RuntimeTestVar
import org.smoothcrawler.implement.component.data.DataHandlerImpl
import org.smoothcrawler.implement.component.data.HttpResponseParserImpl
import org.smoothcrawler.implement.component.httpio.HttpRequestImpl
import java.net.http.HttpResponse

class DataHandlerImplementTest {

//    private lateinit var httpRequest: BaseHttpRequest
//
//    private lateinit var httpResponseParser: BaseHttpResponseParser
//
//    private lateinit var underTest: BaseDataHandler

    private lateinit var httpRequest: HttpRequestImpl

    private lateinit var httpResponseParser: HttpResponseParserImpl

    private lateinit var underTest: DataHandlerImpl

    @BeforeEach
    fun initial() {
        httpRequest = HttpRequestImpl()
        httpResponseParser = HttpResponseParserImpl()
        underTest = DataHandlerImpl()
    }

    @Test
    fun `test implementing BaseDataHandler*process`() {
        val stockDate = RuntimeTestVar.DefaultData
        val stockNo = RuntimeTestVar.DefaultStockNo

        val response: HttpResponse<String> = httpRequest.request("${RuntimeTestVar.URL}${RuntimeTestVar.httpGetRequestParams(date = stockDate, stockNo = stockNo)}")
        val parsedDataFromHttpResponse: String = httpResponseParser.parseContent(response = response)
        val handledData = underTest.process(data = parsedDataFromHttpResponse)
        assertThat(handledData["stat"]).isEqualTo("OK")
        assertThat(handledData["date"]).isEqualTo(stockDate)
        assertThat(handledData["title"]).isEqualTo("111年06月 $stockNo 台積電           各日成交資訊")
        assertThat(handledData["fields"]).isExactlyInstanceOf(JSONArray::class.java)
        (handledData["fields"] as JSONArray).map {
            assertThat(it).isIn(listOf("日期","成交股數","成交金額","開盤價","最高價","最低價","收盤價","漲跌價差","成交筆數"))
        }
        assertThat(handledData["data"]).isExactlyInstanceOf(JSONArray::class.java)
        MatcherAssert.assertThat((handledData["data"] as JSONArray)[0].toString(), CoreMatchers.containsString("[\"111/06/01\",\"32,970,903\",\"18,171,598,472\",\"550.00\",\"555.00\",\"548.00\",\"549.00\",\"-11.00\",\"33,456\"]"))
    }

}