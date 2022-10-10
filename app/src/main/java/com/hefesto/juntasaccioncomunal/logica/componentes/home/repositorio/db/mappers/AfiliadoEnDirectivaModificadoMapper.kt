package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*

fun AfiliadoEnDirectivaModificadoModel.traerAfiliado_Jac_EstadoAfiliacionEntity() : Afiliado_Jac_EstadoAfiliacionEntity {
    return Afiliado_Jac_EstadoAfiliacionEntity(
        afiliadoId = this.afiliadoId,
        rolEnLaAppId = this.rolApp.traerId(),
        estadoAfiliacionId = this.estadoAfiliacion.traerId(),
        fechaActualizacion = Date().convertirAFormato(formato = FormatosFecha.ISO_8610)
    )
}