package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*

//region elementos unicos
fun JACRegistroModel.convertirAJACEntity() : JACEntity {
    return JACEntity(
        jacID = this.jacId,
        nombreJAC = this.NombreJAC,
        codigoJAC = this.CodigoJAC,
    )
}

fun JACEntity.convertirAJacDisponibleParaAfiliacion() : JACDisponibleParaAfiliadoModel {
    return JACDisponibleParaAfiliadoModel(
        id = this.jacID!!,
        nombreJAC = this.nombreJAC!!,
        codigoJAC = this.codigoJAC!!
    )
}

fun JACRegistroModel.traerCorreoEntity() : CorreosEntity {
    return CorreosEntity(
        registro = null,
        correo = this.Correo,
        fechaRegistro = Date().convertirAFormato(formato = FormatosFecha.ISO_8610)
    )
}

fun JACRegistroModel.traerCredencialesSesionEntity() : CredencialesSesionEntity {
    return CredencialesSesionEntity(
        registro = null,
        contrasenia = this.Contrasenia,
        correoId = null
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