package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.panelControl.TraerFuncionalidadesCasoUso
import javax.inject.Inject

class PanelControlFragmenUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerFuncionalidadesCasoUso: TraerFuncionalidadesCasoUso
): BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun traerFuncionalidadesDisponibles() = traerFuncionalidadesCasoUso.invoke()

}