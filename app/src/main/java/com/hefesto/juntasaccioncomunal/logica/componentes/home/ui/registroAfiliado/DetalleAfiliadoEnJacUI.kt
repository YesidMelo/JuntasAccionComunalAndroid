package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarComitesJacHomeCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarEstadosAfiliacionJACHomeCasoUso
import javax.inject.Inject

class DetalleAfiliadoEnJacUI  constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var cargarComitesJacHomeCasoUso: CargarComitesJacHomeCasoUso,
    @JvmField @Inject var cargarEstadosAfiliacionJACHomeCasoUso: CargarEstadosAfiliacionJACHomeCasoUso,
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones)  {

    fun traerEscuchadorExcepciones() = escuchadorExcepciones.invoke()

    fun traerListaComitesEnJac() = cargarComitesJacHomeCasoUso.invoke()

    fun traerListaEstadosAfiliado() = cargarEstadosAfiliacionJACHomeCasoUso.invoke()
}