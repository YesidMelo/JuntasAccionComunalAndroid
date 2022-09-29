package com.hefesto.juntasaccioncomunal.di.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.di.ui.personalizado.DaggerFragmentPersonalizado

abstract class BaseFragmentDagger<T: ViewModel> : DaggerFragmentPersonalizado() {

    //region variables
    private var viewModel: T? = null
    //endregion

    abstract fun traerViewModel() : T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = traerViewModel()
    }
}