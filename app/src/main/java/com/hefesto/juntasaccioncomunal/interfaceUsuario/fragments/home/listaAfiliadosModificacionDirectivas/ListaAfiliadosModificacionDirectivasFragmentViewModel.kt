package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ListaAfiliadosModificacionDirectivasFragmentUI
import javax.inject.Inject

class ListaAfiliadosModificacionDirectivasFragmentViewModel constructor(
    @JvmField @Inject var listaAfiliadosModificacionDirectivasFragmentUI: ListaAfiliadosModificacionDirectivasFragmentUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = listaAfiliadosModificacionDirectivasFragmentUI
}