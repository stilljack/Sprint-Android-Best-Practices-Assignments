package com.example.m01guided.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainViewModel : ViewModel() {
    var job = Job()
    var ioScope = CoroutineScope(Dispatchers.IO + job)
    var uiScope = CoroutineScope(Dispatchers.Main + job)
}
