package com.androidrncustomviewmanager;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class CustomNativeViewManager extends SimpleViewManager<CustomView> {
    @Override
    public String getName() {
        return "CustomView";
    }

    @Override
    protected CustomView createViewInstance( ThemedReactContext reactContext) {
        CustomView customView = new CustomView(reactContext);
        return customView;
    }

    @ReactProp(name = "custom_view")
    public void setCustomView(CustomView customView, ReadableMap data) {
        if(data.hasKey("text2")) {
            customView.setCustomText(data.getString("text2"));
        }
        if(data.hasKey("resId")) {
            customView.setCustomImage(0);
        }
    }

}
