package com.androidrncustomviewmanager

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomViewKotlin internal constructor(context: Context) : LinearLayout(context) {
    lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    init {
        init(context)
    }
    private fun init(context: Context) {
        inflate(context, R.layout.native_custom_layout,this)
        editText = findViewById(R.id.custom_edit_text)
        textView = findViewById(R.id.custom_text_view)
        imageView = findViewById(R.id.custom_image_view)
    }

    fun setCustomText( text2 : String) {
        textView.text = text2
    }

    fun setCustomImage(resId: Int) {
       // imageView.setImageResource(resId)
        if (resId != 0) {
            imageView.setImageResource(resId)
        } else {
            imageView.setImageResource(R.drawable.example_image)
        }
    }

}