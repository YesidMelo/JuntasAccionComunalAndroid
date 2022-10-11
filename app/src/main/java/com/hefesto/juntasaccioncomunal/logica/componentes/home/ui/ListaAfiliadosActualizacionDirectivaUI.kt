package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas.TraerListaAfiliadosModificacionDirectivasCasoUso
import javax.inject.Inject

class ListaAfiliadosActualizacionDirectivaUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaAfiliadosModificacionDirectivasCasoUso : TraerListaAfiliadosModificacionDirectivasCasoUso
): BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun traerListaAfiliadosModificacionDirectiva() = traerListaAfiliadosModificacionDirectivasCasoUso.invoke()
}