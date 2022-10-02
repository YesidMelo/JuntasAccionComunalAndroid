package com.hefesto.juntasaccioncomunal.logica.componentes.login.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarJACCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import javax.inject.Inject


class RegistrarJACFragmentUI constructor(
    @JvmField @Inject var registrarJACCasoUso: RegistrarJACCasoUso,
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) :  BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun registrarJAC(jacRegistroModel: JACRegistroModel) = registrarJACCasoUso.invoke(jacRegistroModel = jacRegistroModel)
}