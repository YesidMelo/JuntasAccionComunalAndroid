package com.hefesto.juntasaccioncomunal.ui.activities.splash

import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import javax.inject.Inject

class SplashActivityViewModel constructor(
    @JvmField @Inject var splashUI: SplashUI
) : ViewModel() {
    fun iniciarPrecarga() = splashUI.iniciarPrecargaAplicacion()
}