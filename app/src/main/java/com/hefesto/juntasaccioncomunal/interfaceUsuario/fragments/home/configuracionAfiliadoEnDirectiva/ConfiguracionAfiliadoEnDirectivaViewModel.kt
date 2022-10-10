package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaViewModel constructor(
    @JvmField @Inject var configuracionAfiliadoEnDirectivaUI: ConfiguracionAfiliadoEnDirectivaUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = configuracionAfiliadoEnDirectivaUI

}