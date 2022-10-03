package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

class JACDisponibleParaAfiliadoModel(
    val id: Int,
    val nombreJAC: String,
    val codigoJAC: String
) {
    override fun toString(): String {
        return nombreJAC
    }
}