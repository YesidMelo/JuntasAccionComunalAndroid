package com.hefesto.juntasaccioncomunal.logica.componentes.login.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import javax.inject.Inject

class IniciarSesionFragmentUI constructor(
    @JvmField @Inject var iniciarSesionCasoUso: IniciarSesionCasoUso,
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones) {

    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) = iniciarSesionCasoUso.invoke(usuarioInicioSesionModel = usuarioInicioSesionModel)
}