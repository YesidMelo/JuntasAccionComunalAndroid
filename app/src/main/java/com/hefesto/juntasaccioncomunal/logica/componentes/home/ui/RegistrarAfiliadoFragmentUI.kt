package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas.TraerListaAfiliadosModificacionDirectivasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.TraerListaAfiliadosRegistroActualizacionCasoUso
import javax.inject.Inject

class RegistroActualizacionAfiliadoUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaAfiliadosRegistroActualizacionCasoUso: TraerListaAfiliadosRegistroActualizacionCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun traerEscuchadorExcepciones() = escuchadorExcepciones.invoke()
    fun traerListaAfiliadosActualizacionRegistro() = traerListaAfiliadosRegistroActualizacionCasoUso.invoke()
}