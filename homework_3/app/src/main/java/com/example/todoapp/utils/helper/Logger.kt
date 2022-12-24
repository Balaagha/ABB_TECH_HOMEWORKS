package com.example.todoapp.utils.helper

import android.util.Log
import com.example.todoapp.utils.Constants.Companion.LOG_TAG_GENERAL

fun String.logI(tag: String = LOG_TAG_GENERAL){
    Log.i(tag,this)
}

fun log(value: String?, tag: String = LOG_TAG_GENERAL){
    Log.i(tag,value.toString())
}

