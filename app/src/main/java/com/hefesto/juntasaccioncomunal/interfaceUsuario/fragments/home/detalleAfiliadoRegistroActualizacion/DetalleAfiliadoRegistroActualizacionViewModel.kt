package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoRegistroActualizacionUI
import javax.inject.Inject

class DetalleAfiliadoRegistroActualizacionViewModel constructor(
    @JvmField @Inject var detalleAfiliadoRegistroActualizacionUI: DetalleAfiliadoRegistroActualizacionUI
)  : BaseViewModel(){

    override fun traerBaseUI(): BaseUI = detalleAfiliadoRegistroActualizacionUI

}