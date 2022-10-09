package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.PanelControlFragmenUI
import javax.inject.Inject

class PanelControlFragmentViewModel constructor(
    @JvmField @Inject var panelControlFragmenUI: PanelControlFragmenUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = panelControlFragmenUI

    fun traerFuncionalidades() = panelControlFragmenUI.traerFuncionalidadesDisponibles()
}