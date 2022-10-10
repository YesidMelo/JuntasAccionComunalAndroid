package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerEstadosAfiliadoEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerRolesAfiliadosEnDirectivaCasoUso
import javax.inject.Inject

class ConfiguracionAfiliadoEnDirectivaUI constructor(
  @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
  @JvmField @Inject var traerEstadosAfiliadoEnDirectivaCasoUso: TraerEstadosAfiliadoEnDirectivaCasoUso,
  @JvmField @Inject var traerRolesAfiliadosEnDirectivaCasoUso: TraerRolesAfiliadosEnDirectivaCasoUso,
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

  fun traerListaEstadosAfiliadoEnDirectiva() = traerEstadosAfiliadoEnDirectivaCasoUso.invoke()

  fun traerListaRolesAfiliadosEnDirectiva() = traerRolesAfiliadosEnDirectivaCasoUso.invoke()

}