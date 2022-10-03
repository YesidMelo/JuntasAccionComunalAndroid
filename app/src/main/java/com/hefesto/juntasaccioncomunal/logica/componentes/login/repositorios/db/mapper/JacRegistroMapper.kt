package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel

//region elementos unicos
fun JACRegistroModel.convertirAJACEntity() : JACEntity {
    return JACEntity(
        jacID = this.jacId,
        nombreJAC = this.NombreJAC,
        codigoJAC = this.CodigoJAC,
        correoJAC = this.Correo,
        contraseniaJAC = this.Contrasenia
    )
}

fun JACEntity.convertirAJacDisponibleParaAfiliacion() : JACDisponibleParaAfiliadoModel {
    return JACDisponibleParaAfiliadoModel(
        id = this.jacID!!,
        nombreJAC = this.nombreJAC!!,
        codigoJAC = this.codigoJAC!!
    )
}
//endregion

//region lista elementos

fun List<JACEntity>.convertirAListaJacDisponibleParaAfiliacion() : List<JACDisponibleParaAfiliadoModel>{
    val lista = emptyList<JACDisponibleParaAfiliadoModel>().toMutableList()
    forEach { lista.add(it.convertirAJacDisponibleParaAfiliacion()) }
    return lista
}

//endregion