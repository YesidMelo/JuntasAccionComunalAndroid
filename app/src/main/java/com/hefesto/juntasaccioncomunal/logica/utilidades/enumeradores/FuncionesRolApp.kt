package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class FuncionesRolApp constructor(
    private val id: Int,
    private val nombre: String,
    private val rolEncargado: RolesEnApp,
    //solo de cara de interface usuario
    @RawRes private val lottieRes: Int,
    @StringRes private val stringRes: Int
) {
    ASIGNAR_ROL_DENTRO_DE_JAC(id = 1, nombre = "asignar rol dentro de jac", rolEncargado = RolesEnApp.JAC, lottieRes = R.raw.lottie_modificacion_afiliados, stringRes = R.string.asignar_rol_dentro_directiva),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    fun traerRolEncargado() = rolEncargado
    @RawRes fun traerLottieRes() = lottieRes
    fun traerStringRes() = stringRes

    companion object {
        fun traerFuncionRolPorId(id : Int) : FuncionesRolApp? {
            for (funcion in values()) {
                if (id != funcion.id) continue
                return funcion
            }
            return null
        }
    }
}