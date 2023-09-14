package com.androidrncustomviewmanager
import android.os.Looper
import android.widget.EditText
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.RCTEventEmitter
import java.util.logging.Handler

class CustomNativeViewManagerKotlin: SimpleViewManager<CustomViewKotlin>() {
    override fun getName(): String {
        return "CustomViewKotlin"
    }
    override fun createViewInstance(reactContext: ThemedReactContext): CustomViewKotlin {
        return CustomViewKotlin(reactContext)
    }

    @ReactProp(name = "custom_view_kotlin")
    fun setCustomViewKotlin(customView: CustomViewKotlin, data: ReadableMap) {
        if(data.hasKey("text")) {
            customView.setCustomText(data.getString("text")!!)
        }
        if(data.hasKey("resId")) {
            customView.setCustomImage(0)
        }
    }

}