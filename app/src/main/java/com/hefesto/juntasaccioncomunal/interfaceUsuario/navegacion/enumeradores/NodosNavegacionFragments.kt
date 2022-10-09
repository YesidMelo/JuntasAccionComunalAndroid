package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class NodosNavegacionFragments constructor( @StringRes var titulo: Int? = null, @StringRes var subtitulo : Int? = null) {
    INICIAR_SESION,
    REGISTRAR_JAC,
    REGISTRAR_AFILIADO,
    PANEL_CONTROL(titulo = R.string.panel_control),
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS(titulo = R.string.lista_afiliados_modificacion_directivas),
    ;
}