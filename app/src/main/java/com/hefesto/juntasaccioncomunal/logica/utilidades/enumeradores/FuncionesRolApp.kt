package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.hefesto.juntasaccioncomunal.R

enum class FuncionesRolApp constructor(
    private val id: Int,
    private val nombre: String,
    private val rolesEncargado: Array<RolesEnApp>,
    //solo de cara de interface usuario
    @RawRes private val lottieRes: Int,
    @StringRes private val stringRes: Int
) {
    ASIGNAR_ROL_DENTRO_DE_JAC(id = 1, nombre = "asignar rol dentro de jac", rolesEncargado = arrayOf(RolesEnApp.JAC), lottieRes = R.raw.lottie_modificacion_afiliados, stringRes = R.string.asignar_rol_dentro_directiva),
    REGISTRAR_AFILIADO_JAC(id = 2, nombre = "registrar afiliado en jac", rolesEncargado = arrayOf(RolesEnApp.JAC, RolesEnApp.SECRETARIO), lottieRes = R.raw.lottie_registro_afiliados, stringRes = R.string.registrar_afiliado),
    CREAR_LISTA_REUNION_JAC(id = 3, nombre = "Crear lista reunion jac", rolesEncargado = arrayOf(RolesEnApp.JAC, RolesEnApp.SECRETARIO), lottieRes = R.raw.lottie_reunion, stringRes = R.string.crear_lista_reunion),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    fun traerRolesEncargados() = rolesEncargado

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