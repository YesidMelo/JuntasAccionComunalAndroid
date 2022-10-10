package com.hefesto.juntasaccioncomunal.logica.modelos.general

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

class RolAppModel constructor(
    var rolesEnApp: RolesEnApp,
    var nombre: String
) {
    override fun toString(): String = nombre
}