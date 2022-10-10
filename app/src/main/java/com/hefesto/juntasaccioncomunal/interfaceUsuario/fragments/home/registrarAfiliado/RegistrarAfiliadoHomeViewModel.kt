package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.RegistrarAfiliadoUI
import javax.inject.Inject

class RegistrarAfiliadoHomeViewModel constructor(
    @JvmField @Inject var registrarAfiliadoUI: RegistrarAfiliadoUI
) : BaseViewModel(){

    override fun traerBaseUI(): BaseUI = registrarAfiliadoUI

}