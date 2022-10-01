package com.hefesto.juntasaccioncomunal.ui.activities.splash

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class SplashActivityViewModel constructor(
    @JvmField @Inject var splashUI: SplashUI
) : BaseViewModel() {
    fun iniciarPrecarga() = splashUI.iniciarPrecargaAplicacion()

    override fun traerBaseUI(): BaseUI = splashUI
}