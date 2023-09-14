package com.androidrncustomviewmanager

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.UIManagerModule
import org.json.JSONObject

class CustomViewModule internal constructor(private var reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {
    /**
     * returns the name of the module to access it from React Native
     */
    override fun getName(): String {
        return "CustomViewModule"
    }

    /**
     *  React Method to send the entered value  in editText to React Native
     */
    @ReactMethod
    fun getTextFromET(reactTag: Int, promise: Promise) {
        reactContext.runOnUiQueueThread {
            try {
                val uiManager = reactContext.getNativeModule(UIManagerModule::class.java)
                val view = uiManager?.resolveView(reactTag) as? CustomViewKotlin
                view?.let {
                    promise.resolve(it.editText.text.toString())
                } ?: promise.reject("ERROR", "CustomView not found.")
            } catch (e: Exception) {
                promise.reject("ERROR", "Failed to get text: $e")
            }
        }
    }

    /**
     *  React Method to receive String array from React Native an show in android
     */
    @ReactMethod
    fun receiveStringArray( data: ReadableArray) {
        for (i in 0 until data.size()) {
            Log.i("Android", "${data.getString(i)}")
        }
    }

    @ReactMethod
    fun receiveString( data: String) {
        Log.i("AndroidString", data)

    }

    @ReactMethod
    fun receiveInt(data: Int) {
        Log.i("AndroidInt", data.toString())
    }

    @ReactMethod
    fun receiveBoolean(data: Int) {
        Log.i("AndroidBoolean", data.toString())
    }

    @ReactMethod
    fun receiveJSON(jasonData: ReadableMap) {
        Log.i("AndroidJasonObject", "${jasonData.getString("name").toString()} ${jasonData.getDouble("age")}")
    }

    @ReactMethod
    fun sendString(reactTag: Int, promise: Promise) {
        reactContext.runOnUiQueueThread {
            try {
                val uiManager = reactContext.getNativeModule(UIManagerModule::class.java)
                val view = uiManager?.resolveView(reactTag) as? CustomViewKotlin
                view?.let {
                    promise.resolve("String")
                } ?: promise.reject("ERROR", "CustomView not found.")
            } catch (e: Exception) {
                promise.reject("ERROR", "Failed to get text: $e")
            }
        }
    }

    @ReactMethod
    fun sendInt(reactTag: Int , promise: Promise) {
        reactContext.runOnUiQueueThread {
            try {
                val uiManager = reactContext.getNativeModule(UIManagerModule::class.java)
                val view = uiManager?.resolveView(reactTag) as? CustomViewKotlin
                view?.let {
                    promise.resolve(40)
                } ?: promise.reject("ERROR", "CustomView not found.")
            } catch (e: Exception) {
                promise.reject("ERROR", "Failed to get text: $e")
            }
        }
    }


    @ReactMethod
    fun getJsonObject(callback: Callback) {
        val jsonObject = JSONObject()
        jsonObject.put("name", "Ayush")
        jsonObject.put("age", 28)
        callback.invoke(null, convertJsonToMap(jsonObject))
    }
    private fun convertJsonToMap(jsonObject: JSONObject): WritableMap {
        val map = Arguments.createMap()

        val iterator = jsonObject.keys()
        while (iterator.hasNext()) {
            val key = iterator.next()
            when (val value = jsonObject.get(key)) {
                is JSONObject -> map.putMap(key, convertJsonToMap(value))
                is Boolean -> map.putBoolean(key, value)
                is Int -> map.putInt(key, value)
                is Double -> map.putDouble(key, value)
                is String -> map.putString(key, value)
                else -> map.putString(key, value.toString())
            }
        }
        return map
    }


}