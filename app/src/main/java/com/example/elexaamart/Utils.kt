package com.example.elexaamart

import android.widget.EditText

fun EditText.isEmply():Boolean{

    return if (this.text.toString().isEmpty()){
        this.error = "Please fill this field"
        true
    }else{
        false
    }
}