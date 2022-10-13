package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarTiposDocumentoCasoUso
import javax.inject.Inject

class DatosBasicosAfiliadoUI  constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var cargarTiposDocumentoCasoUso : CargarTiposDocumentoCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones)  {

    fun traerEscuchadorExcepciones() = escuchadorExcepciones.invoke()
    fun traerListaTiposDocumento() = cargarTiposDocumentoCasoUso.invoke()
}