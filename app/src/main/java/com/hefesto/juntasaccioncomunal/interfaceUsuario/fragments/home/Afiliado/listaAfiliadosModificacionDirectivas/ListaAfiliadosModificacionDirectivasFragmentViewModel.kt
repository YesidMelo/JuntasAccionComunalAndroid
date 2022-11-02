package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.listaAfiliadosModificacionDirectivas

import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ListaAfiliadosActualizacionDirectivaUI
import javax.inject.Inject

class ListaAfiliadosModificacionDirectivasFragmentViewModel constructor(
    @JvmField @Inject var listaAfiliadosActualizacionDirectivaUI: ListaAfiliadosActualizacionDirectivaUI
) : BaseViewModel() {

    override fun traerBaseUI(): BaseUI = listaAfiliadosActualizacionDirectivaUI

    fun traerListaAfiliadosModificacionDirectiva() = listaAfiliadosActualizacionDirectivaUI.traerListaAfiliadosModificacionDirectiva()
}