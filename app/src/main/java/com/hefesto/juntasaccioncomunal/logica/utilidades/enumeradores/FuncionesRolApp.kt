package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class FuncionesRolApp constructor(
    private val id: Int,
    private val nombre: String,
    private val rolEncargado: RolesEnApp,
) {
    ASIGNAR_ROL_DENTRO_DE_JAC(id = 1, nombre = "asignar rol dentro de jac", rolEncargado = RolesEnApp.JAC),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
    fun traerRolEncargado() = rolEncargado

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