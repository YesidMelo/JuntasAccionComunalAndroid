package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

class JACDisponibleParaAfiliadoModel(
    val id: Int,
    val nombreJAC: String,
    val codigoJAC: String
) : BaseModel() {
    override fun toString(): String {
        return nombreJAC
    }
}