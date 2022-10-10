package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaUI constructor(
  @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {
}