package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.RegistrarAfiliadoHomeCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import javax.inject.Inject

class DetalleAfiliadoRegistroActualizacionUI constructor(
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var registrarAfiliadoHomeCasoUso : RegistrarAfiliadoHomeCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun traerEscuchadorExcepciones() = escuchadorExcepciones.invoke()

    fun registrarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
        = registrarAfiliadoHomeCasoUso.invoke(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
}