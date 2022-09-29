package com.hefesto.juntasaccioncomunal.di.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.personalizado.DaggerActivityPersonalizado


abstract class BaseActivityDagger<T: ViewModel> : DaggerActivityPersonalizado() {
    
    private var viewModel : T? = null

    abstract fun getViewModel() : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if(viewModel == null) getViewModel() else this.viewModel
    }
}