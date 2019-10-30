package com.example.m02guided

import com.crashlytics.android.Crashlytics

fun dropBreadCrumbs(className:String){
    Crashlytics.log(className)
}