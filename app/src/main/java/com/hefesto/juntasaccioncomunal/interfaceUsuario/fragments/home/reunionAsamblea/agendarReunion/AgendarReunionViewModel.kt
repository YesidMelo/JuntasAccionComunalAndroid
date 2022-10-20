package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.AgendarReunionUI
import javax.inject.Inject

class AgendarReunionViewModel constructor(
    @JvmField @Inject var agendarReunionUI: AgendarReunionUI
): BaseViewModel(){

    override fun traerBaseUI(): BaseUI = agendarReunionUI

}