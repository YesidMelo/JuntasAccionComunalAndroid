package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.R

enum class AccionesNavGrap constructor(@IdRes private val actionId : Int) {
    INICIAR_SESION_A_REGISTRAR_AFILIADO(actionId = R.id.action_iniciar_sesion_to_registrar_afiliado),
    INICIAR_SESION_A_REGISTRAR_JAC(actionId = R.id.action_iniciar_sesion_to_registrar_jac),
    REGISTRAR_AFILIADO_A_INICIAR_SESION(actionId = R.id.action_registrar_afiliado_to_iniciar_sesion),
    REGISTRAR_JAC_A_INICIAR_SESION(actionId = R.id.action_registrar_jac_to_iniciar_sesion),
    PANEL_CONTROL_A_LISTA_AFILIADOS_MODIFICACION_DIRECTIVA(actionId = R.id.action_panel_control_to_listaAfiliadosModificacionDirectiva),
    LISTA_AFILIADOS_MODIFICACION_DIRECTIVA_A_PANEL_CONTROL(actionId = R.id.action_listaAfiliadosModificacionDirectiva_to_panel_control),
    ;

    @IdRes fun traerIdAccion() = actionId
}