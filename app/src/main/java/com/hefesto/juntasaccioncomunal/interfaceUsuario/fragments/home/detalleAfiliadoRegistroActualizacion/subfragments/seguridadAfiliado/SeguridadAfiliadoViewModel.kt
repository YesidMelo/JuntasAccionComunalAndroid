package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.seguridadAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.SeguridadAfiliadoUI
import javax.inject.Inject

class SeguridadAfiliadoViewModel constructor(
    @JvmField @Inject var seguridadAfiliadoUI: SeguridadAfiliadoUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = seguridadAfiliadoUI

}