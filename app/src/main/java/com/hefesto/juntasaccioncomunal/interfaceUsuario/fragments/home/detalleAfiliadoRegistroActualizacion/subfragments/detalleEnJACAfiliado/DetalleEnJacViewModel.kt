package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoEnJacUI
import javax.inject.Inject

class DetalleEnJacViewModel constructor(
    @JvmField @Inject var detalleAfiliadoEnJacUI: DetalleAfiliadoEnJacUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = detalleAfiliadoEnJacUI

}