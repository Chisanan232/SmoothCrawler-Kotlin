package org.smoothcrawler.implement.component.data

import org.json.JSONObject
import org.smoothcrawler.component.data.BaseDataHandler

class DataHandlerImpl: BaseDataHandler {

    override fun process(data: Any): JSONObject {
        // Parse string type data into json object
        return JSONObject(data.toString())
    }

}