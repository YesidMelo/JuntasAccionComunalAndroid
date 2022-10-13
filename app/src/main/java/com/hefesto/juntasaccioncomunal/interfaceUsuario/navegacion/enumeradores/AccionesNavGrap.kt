package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.R

enum class AccionesNavGrap constructor(@IdRes private val actionId : Int) {
    //region splash/login
    INICIAR_SESION_A_REGISTRAR_AFILIADO(actionId = R.id.action_iniciar_sesion_to_registrar_afiliado),
    INICIAR_SESION_A_REGISTRAR_JAC(actionId = R.id.action_iniciar_sesion_to_registrar_jac),
    REGISTRAR_AFILIADO_A_INICIAR_SESION(actionId = R.id.action_registrar_afiliado_to_iniciar_sesion),
    REGISTRAR_JAC_A_INICIAR_SESION(actionId = R.id.action_registrar_jac_to_iniciar_sesion),
    //endregion
    //region home
    //region directivas jac
    PANEL_CONTROL_A_LISTA_AFILIADOS_MODIFICACION_DIRECTIVA(actionId = R.id.action_panel_control_to_listaAfiliadosModificacionDirectiva),
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVA_A_PANEL_CONTROL(actionId = R.id.action_listaAfiliadosModificacionDirectiva_to_panel_control),
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVA_A_CONFIGURACION_AFILIADO_EN_DIRECTIVA(actionId = R.id.action_listaAfiliadosModificacionDirectiva_to_configuracionAfiliadoEnDirectiva),
    CONFIGURACION_AFILIADO_EN_DIRECTIVA_A_LISTA_AFILIADOS_MODIFICACION_DIRECTIVA(actionId = R.id.action_configuracionAfiliadoEnDirectiva_to_listaAfiliadosModificacionDirectiva),
    //endregion
    //region registro afiliado
    PANEL_CONTROL_A_REGISTRAR_AFILIADO_HOME(actionId = R.id.action_panel_control_to_registrar_afiliado_home),
    REGISTRAR_AFILIADO_HOME_A_PANEL_CONTROL(actionId = R.id.action_registrar_afiliado_home_to_panel_control),
    REGISTRAR_AFILIADO_HOME_A_DETALLE_AFILIADO_HOME(actionId = R.id.action_registrar_afiliado_home_to_detalle_afiliado_a_registrar_actualizar),
    DETALLE_AFILIADO_HOME_A_REGISTRAR_AFILIADO_HOME(actionId = R.id.action_detalle_afiliado_a_registrar_actualizar_to_registrar_afiliado_home)
    //endregion
    //endregion
    ;

    @IdRes fun traerIdAccion() = actionId
}