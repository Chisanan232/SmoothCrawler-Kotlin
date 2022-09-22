package org.smoothcrawler.implement.component.data

import org.smoothcrawler.component.data.BaseHttpResponseParser
import java.net.http.HttpResponse

class HttpResponseParserImpl: BaseHttpResponseParser {

    override fun parseContent(response: Any): String {
        return (response as HttpResponse<String>).body()
    }

}