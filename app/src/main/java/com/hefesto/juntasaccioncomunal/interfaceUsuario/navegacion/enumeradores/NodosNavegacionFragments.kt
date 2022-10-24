package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.ListaAfiliadosModificacionDirectivasFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.RegistrarAfiliadoHomeFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.AgendarReunionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.ListaReunionesCreacionActaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.iniciarSesion.IniciarSesionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarAfiliado.RegistrarAfiliadoFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC.RegistrarJACFragment

enum class NodosNavegacionFragments constructor(
    @StringRes val titulo: Int? = null,
    @StringRes val subtitulo : Int? = null,
    val fragment : Class<*>? = null,
    val identificador: String?= null
) {
    //region splash/login
    INICIAR_SESION(fragment = IniciarSesionFragment::class.java),
    REGISTRAR_JAC(fragment = RegistrarJACFragment::class.java, identificador = "RegistrarJAC"),
    REGISTRAR_AFILIADO(fragment = RegistrarAfiliadoFragment::class.java, identificador = "RegistrarAfiliado"),
    //endregion
    //region home
    PANEL_CONTROL(titulo = R.string.panel_control, fragment = PanelControlFragment::class.java),

    //region afiliados
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVAS(titulo = R.string.lista_afiliados_modificacion_directivas, fragment = ListaAfiliadosModificacionDirectivasFragment::class.java, identificador = "ListaAfiliadoDirectivas"),
    CONFIGURACION_AFILIADO_EN_DIRECTIVA(titulo = R.string.configuracion_afiliado_en_directiva, fragment = ConfiguracionAfiliadoEnDirectivaFragment::class.java, identificador = "ConfiguracionAfiliadoEnDirectiva"),
    REGISTRAR_AFILIADO_HOME(titulo = R.string.registrar_afiliado, fragment = RegistrarAfiliadoHomeFragment::class.java, identificador = "RegistrarAfiliadoHome"),
    CONTACTO_AFILIADO_HOME,
    DATOS_AFILIADO_HOME,
    DETALLE_JAC_AFILIADO_HOME,
    SEGURIDAD_AFILIADO_HOME,
    DETALLE_AFILIADO_REGISTRO_HOME(titulo = R.string.formulario_registro_afiliado, fragment = DetalleAfiliadoRegistroActualizacionFragment::class.java, identificador = "DetalleAfiliadoRegistroHome"),
    //endregion

    //region reuniones asambleas
    AGENDAR_REUNION_ASAMBLEA(titulo = R.string.agendar_reunion_asamblea, fragment = AgendarReunionFragment::class.java, identificador = "agendar reunion asamblea"),
    CREAR_ACTA_REUNION_ASAMBLEA(titulo = R.string.crear_acta_reunion, fragment = CrearActaFragment::class.java, identificador = "crear acta reunion asamblea"),
    LISTA_REUNIONES_SIN_ACTAS(titulo = R.string.lista_reunion_creacion_acta, fragment = ListaReunionesCreacionActaFragment::class.java, identificador = "lista crear acta reunion"),
    PUNTO_REUNION,
    DETALLE_PUNTO_REUNION,
    //endregion

    //endregion
    ;
}