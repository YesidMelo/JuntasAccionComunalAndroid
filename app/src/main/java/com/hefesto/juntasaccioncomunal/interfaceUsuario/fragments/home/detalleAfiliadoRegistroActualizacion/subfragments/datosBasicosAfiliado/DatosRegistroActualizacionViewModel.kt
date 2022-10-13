package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DatosBasicosAfiliadoUI
import javax.inject.Inject

class DatosRegistroActualizacionViewModel constructor(
    @JvmField @Inject var datosBasicosAfiliadoUI: DatosBasicosAfiliadoUI
): BaseViewModel(){

    override fun traerBaseUI(): BaseUI = datosBasicosAfiliadoUI

}