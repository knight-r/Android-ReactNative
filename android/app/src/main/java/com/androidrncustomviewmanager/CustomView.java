package com.androidrncustomviewmanager;

import static com.androidrncustomviewmanager.R.drawable.ic_android_black_24dp;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomView extends LinearLayout {
    private EditText editText;
    private TextView textView;
    private ImageView imageView;

    public CustomView(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context) {
        inflate(context, R.layout.native_custom_layout,this);
        editText = (EditText) findViewById(R.id.custom_edit_text);
        textView = findViewById(R.id.custom_text_view);
        imageView = findViewById(R.id.custom_image_view);

    }

    public String getCustomText() {
        return editText.getText().toString();
    }
    public void setCustomText(String text) {
        textView.setText(text);
    }

    public void setCustomImage(Integer resId) {
        if (resId != 0) {
            imageView.setImageResource(resId);
        } else {
            imageView.setImageResource(ic_android_black_24dp);
        }
    }

}
