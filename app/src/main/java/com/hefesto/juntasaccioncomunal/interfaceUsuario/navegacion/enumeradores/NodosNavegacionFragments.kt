package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class NodosNavegacionFragments constructor( @StringRes var titulo: Int? = null, @StringRes var subtitulo : Int? = null) {
    //region splash/login
    INICIAR_SESION,
    REGISTRAR_JAC,
    REGISTRAR_AFILIADO,
    //endregion
    //region home
    PANEL_CONTROL(titulo = R.string.panel_control),
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS(titulo = R.string.lista_afiliados_modificacion_directivas),
    CONFIGURACION_AFILIADO_EN_DIRECTIVA(titulo = R.string.configuracion_afiliado_en_directiva),
    REGISTRAR_AFILIADO_HOME(titulo = R.string.registrar_afiliado),
    CONTACTO_AFILIADO_HOME,
    DATOS_AFILIADO_HOME,
    DETALLE_JAC_AFILIADO_HOME,
    SEGURIDAD_AFILIADO_HOME,
    DETALLE_AFILIADO_REGISTRO_HOME(titulo = R.string.formulario_registro_afiliado)
    //endregion
    ;
}