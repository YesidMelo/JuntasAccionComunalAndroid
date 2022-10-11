package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoActualizacionRegistroView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate

//region unico elemento

fun AfiliadoActualizacionRegistroView.convertirADatosBasicosAfiliadoActualizarRegistrarInformacionModel() : DatosBasicosAfiliadoActualizarRegistrarInformacionModel{
    return DatosBasicosAfiliadoActualizarRegistrarInformacionModel(
        afiliadoId = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        tipoDocumento = TipoDocumento.traerTipoDocumentoPorId(id = this.tipoDocumento!!),
        documento = this.documento,
        estadoAfiliacion = EstadoAfiliacion.traerEstadoAfiliacionPorId(id = this.estadoAfiliacionId!!),
        rolesEnApp = RolesEnApp.traerRolAppPorId(id = this.rolEnLaAppId!!),
        jacId = this.jacId
    )
}

//endregion

//region lista

fun List<AfiliadoActualizacionRegistroView>.convertirAListaDatosBasicosAfiliadoActualizarRegistrarInformacionModel() : List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel> {
    val lista = emptyList<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>().toMutableList()
    for (item in this) {
        lista.add(item.convertirADatosBasicosAfiliadoActualizarRegistrarInformacionModel())
    }
    return lista
}

//endregion