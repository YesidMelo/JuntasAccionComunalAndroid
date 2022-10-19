package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoActualizacionRegistroView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoContactoRegistroActualizacionView
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado.ContactoAfiliadoRegistroActualizacionViewModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosContactoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
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
        jacId = this.jacId,
        credencialesSesion = this.credencialesSesion
    )
}

fun AfiliadoContactoRegistroActualizacionView.convertirADatosContactoAfiliadoModel() : DatosContactoAfiliadoModel {
    return DatosContactoAfiliadoModel(
        correoElectronicoId = this.correoElectronicoId,
        correo = this.correo,
        fechaRegistroCorreo = this.fechaRegistroCorreo?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        direccionId = this.direccionId,
        direccion = this.direccion,
        afiliadoDireccionId = this.afiliadoDireccionId,
        tipoTelefono = TipoTelefono.buscarTipoTelefonoPorId(id = this.tipoTelefonoId),
        telefonoId = this.telefonoId,
        telefono = this.telefono,
        afiliadoTelefonoId = this.afiliadoTelefonoId
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