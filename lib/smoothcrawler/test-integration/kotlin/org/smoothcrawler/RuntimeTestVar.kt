package org.smoothcrawler

class RuntimeTestVar {

    companion object {
        // TODO: It may be better to use DI to implement this URL value for different testing environment.
//        const val URL: String = "http://127.0.0.1:12345/exchangeReport/STOCK_DAY?response=json&date=20170101&stockNo=2331"
        const val URL: String = "http://127.0.0.1:12345/exchangeReport/STOCK_DAY?"

        const val DefaultResponse: String = "json"
        const val DefaultData: String = "20170101"
        const val DefaultStockNo: String = "2331"

        fun httpGetRequestParams(
            response: String = DefaultResponse,
            date: String = DefaultData,
            stockNo: String = DefaultStockNo,
        ): String {
            val getReqParams = mapOf(
                "response" to response,
                "date" to date,
                "stockNo" to stockNo
            )
            return getReqParams.map { (key, value) -> "${key}=${value}" }.joinToString("&")
        }

        fun targetURL(
            response: String = DefaultResponse,
            date: String = DefaultData,
            stockNo: String = DefaultStockNo,
        ): String {
            return "${URL}${httpGetRequestParams(response = response, date = date, stockNo = stockNo)}"
        }
    }

}