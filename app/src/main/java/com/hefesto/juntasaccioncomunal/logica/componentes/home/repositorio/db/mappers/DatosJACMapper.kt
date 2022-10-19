package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoDetalleEnJacView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosJACModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate

fun AfiliadoDetalleEnJacView.convertirADatosJACModel(): DatosJACModel {
    return DatosJACModel(
        comite = ComitesEnJAC.buscarPorId(id = this.comiteId),
        fechaActualizacionComite = this.fechaActualizacionComite?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        estadoAfiliacion = EstadoAfiliacion.traerEstadoAfiliacionPorId(id = this.estadoAfiliacionId!!),
        fechaActualizacionEstadoAfiliacion = this.fechaActualizacionEstadoAfiliado?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        rolesEnApp = RolesEnApp.traerRolAppPorId(id = this.rolEnLaAppId!!)
    )
}