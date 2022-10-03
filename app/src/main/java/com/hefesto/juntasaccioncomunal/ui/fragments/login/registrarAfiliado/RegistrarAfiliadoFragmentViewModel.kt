package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarAfiliadoFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class RegistrarAfiliadoFragmentViewModel constructor(
    @JvmField @Inject var registrarAfiliadoUI : RegistrarAfiliadoFragmentUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = registrarAfiliadoUI

    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel) = registrarAfiliadoUI.registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)
}