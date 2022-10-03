package com.hefesto.juntasaccioncomunal.logica.componentes.login.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarAfiliadoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerListaJACsRegistradasCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

class RegistrarAfiliadoFragmentUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var registrarAfiliadoCasoUso: RegistrarAfiliadoCasoUso,
    @JvmField @Inject var traerListaJACsRegistradasCasoUso: TraerListaJACsRegistradasCasoUso,
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel) = registrarAfiliadoCasoUso.invoke(afiliadoARegistrarModel = afiliadoARegistrarModel)

    fun traerListaJacsRegistradas() = traerListaJACsRegistradasCasoUso.invoke()

}