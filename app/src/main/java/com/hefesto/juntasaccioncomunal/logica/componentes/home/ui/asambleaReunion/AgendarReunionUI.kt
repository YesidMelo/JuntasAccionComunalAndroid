package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import javax.inject.Inject

class AgendarReunionUI constructor(
    @JvmField @Inject var cargarEscuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = cargarEscuchadorExcepciones) {

}