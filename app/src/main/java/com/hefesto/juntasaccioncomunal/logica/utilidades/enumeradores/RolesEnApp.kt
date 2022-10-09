package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class RolesEnApp constructor(
    private val id: Int,
    private val nombre : String,
    @StringRes private val stringRes:  Int
) {
    ADMINISTRADOR(id = 1, nombre = "Administracion", stringRes = R.string.administracion),
    JAC(id = 2, nombre = "JAC", stringRes = R.string.jac),
    PRESIDENTE(id = 3, nombre = "Presidente", stringRes = R.string.presidente),
    VICEPRESIDENTE(id = 4, nombre = "Vicepresidente",stringRes = R.string.vicepresidente),
    SECRETARIO(id = 5, nombre = "Secretario", stringRes = R.string.secretario),
    FISCAL(id = 6, nombre = "Fiscal", stringRes = R.string.fiscal),
    TESORERO(id = 7, nombre = "Tesorero", stringRes = R.string.tesorero),
    AFILIADO(id = 8, nombre = "Afiliado", stringRes = R.string.afiliado),
    PREAFILIADO(id = 9, nombre = "pre-afiliado", stringRes = R.string.pre_afiliado),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    @StringRes fun traerStringRes() = stringRes

    companion object {

        fun traerRolAppPorId(id: Int) : RolesEnApp {
            for(rol in values()) {
                if(id != rol.traerId()) continue
                return rol
            }
            return PREAFILIADO
        }
    }
}