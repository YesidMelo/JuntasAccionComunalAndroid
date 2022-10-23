package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActa

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.CrearActaReunionUI
import javax.inject.Inject

class CrearActaReunionViewModel constructor(
    @JvmField @Inject var crearActaReunionUI: CrearActaReunionUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = crearActaReunionUI

}