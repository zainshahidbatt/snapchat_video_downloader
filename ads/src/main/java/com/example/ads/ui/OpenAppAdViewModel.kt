package com.example.ads.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpenAppAdViewModel @Inject constructor(): ViewModel() {
    var tryToShowAd = false
}