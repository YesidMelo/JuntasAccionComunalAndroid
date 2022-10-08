package com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.enumeradores

import androidx.annotation.IdRes
import com.hefesto.juntasaccioncomunal.R

enum class AccionesNavGrap constructor(@IdRes private val actionId : Int) {
    INICIAR_SESION_A_REGISTRAR_AFILIADO(actionId = R.id.action_iniciar_sesion_to_registrar_afiliado),
    INICIAR_SESION_A_REGISTRAR_JAC(actionId = R.id.action_iniciar_sesion_to_registrar_jac),
    REGISTRAR_AFILIADO_A_INICIAR_SESION(actionId = R.id.action_registrar_afiliado_to_iniciar_sesion),
    REGISTRAR_JAC_A_INICIAR_SESION(actionId = R.id.action_registrar_jac_to_iniciar_sesion),
    ;

    @IdRes fun traerIdAccion() = actionId
}