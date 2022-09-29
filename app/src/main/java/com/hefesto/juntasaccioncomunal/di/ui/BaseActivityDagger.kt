package com.hefesto.juntasaccioncomunal.di.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.DaggerActivity

abstract class BaseActivityDagger<T: ViewModel> : DaggerActivity() {
    private var viewModel : T? = null

    abstract fun getViewModel() : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if(viewModel == null) getViewModel() else this.viewModel
    }
}