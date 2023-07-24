package com.example.analytics.utils

import android.os.Bundle
import android.util.Log
import org.json.JSONException
import org.json.JSONObject


fun Bundle.toJSONObject(): JSONObject? {
    val json = JSONObject()
    val bundleKeys: Set<String> = keySet()
    for (key in bundleKeys) {
        try {
            json.put(key, get(key))
        } catch (exception: JSONException) {
            Log.d("convertToJSON ", "convertToJSON:..... ${exception.message} ")
            return null
        }
    }
    return json
}

fun Bundle.toHashMap(): HashMap<String, Any>? {
    val hashMap = HashMap<String, Any>()
    val keySet: Set<String> = keySet()
    val iterator: Iterator<String> = keySet.iterator()
    while (iterator.hasNext()) {
        try {
            val key = iterator.next()
            get(key)?.let { hashMap.put(key, it) }
        } catch (exception: java.lang.Exception) {
            Log.d("convertToHashMap", "convertToHashMap:..... ${exception.message} ")
            return null
        }

    }
    return hashMap

}