package com.hefesto.juntasaccioncomunal.ui.base

import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI

abstract class BaseViewModel : ViewModel() {
    abstract fun traerBaseUI() : BaseUI
}