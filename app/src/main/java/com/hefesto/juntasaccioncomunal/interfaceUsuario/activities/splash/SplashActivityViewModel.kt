package com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.splash

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivityViewModel constructor(
    @JvmField @Inject var splashUI: SplashUI
) : BaseViewModel() {

    //region variables
    private val cargo = MutableLiveData<Boolean>()
    //endregion

    fun iniciarPrecarga() : MutableLiveData<Boolean> {
        GlobalScope.launch {
            splashUI
                .iniciarPrecargaAplicacion()
                .collect {
                    cargo.postValue(it)
                }
        }
        return cargo
    }

    override fun traerBaseUI(): BaseUI = splashUI
}