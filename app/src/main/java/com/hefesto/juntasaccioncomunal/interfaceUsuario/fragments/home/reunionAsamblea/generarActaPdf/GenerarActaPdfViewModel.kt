package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.GenerarActaPDFUI
import javax.inject.Inject

class GenerarActaPdfViewModel constructor(
    @JvmField @Inject var generarActaPDFUI: GenerarActaPDFUI
): BaseViewModel() {

    override fun traerBaseUI(): BaseUI = generarActaPDFUI

}