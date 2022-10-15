package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarTiposTelefonoHomeCasoUso
import javax.inject.Inject

class ContactoAfiliadoUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var cargarTiposTelefonoHomeCasoUso : CargarTiposTelefonoHomeCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun traerEscuchadorExcepciones() = escuchadorExcepciones.invoke()

    fun traerListaTiposTelefonos() = cargarTiposTelefonoHomeCasoUso.invoke()

}