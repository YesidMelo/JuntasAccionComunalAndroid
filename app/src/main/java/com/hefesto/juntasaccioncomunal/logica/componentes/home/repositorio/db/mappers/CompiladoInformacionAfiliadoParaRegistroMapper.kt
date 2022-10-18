package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Direccion_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_Comite_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Telefono_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.DireccionesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.TelefonosEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*

fun CompiladoInformacionAfiliadoParaRegistroModel.traerCorreoEntity() : CorreosEntity {
    return CorreosEntity(
        registro = this.contactoParaRegistrarModel?.correoElectronicoId,
        correo = this.contactoParaRegistrarModel?.correo,
        fechaRegistro = Date().convertirAFormato(formato = FormatosFecha.ISO_8610)
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerCredencialesSesionEntity(): CredencialesSesionEntity {
    return CredencialesSesionEntity(
        registro = this.seguridadParaRegistroModel?.credencialesSesionId,
        correoId = this.contactoParaRegistrarModel?.correoElectronicoId,
        contrasenia = this.seguridadParaRegistroModel?.contrasenia
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoEntity() : AfiliadoEntity {
    return AfiliadoEntity(
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId,
        nombres = this.datosBasicosParaRegistrarModel?.nombres,
        apellidos = this.datosBasicosParaRegistrarModel?.apellidos,
        tipoDocumento = this.datosBasicosParaRegistrarModel?.tipoDocumento?.traerId(),
        documento = this.datosBasicosParaRegistrarModel?.numeroDocumento,
        fechaNacimiento = this.datosBasicosParaRegistrarModel?.fechaNacimiento?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        credencialesSesion = this.seguridadParaRegistroModel?.credencialesSesionId
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoCorreoEntity() : Afiliado_Correo_Entity {
    return Afiliado_Correo_Entity(
        correoId = this.contactoParaRegistrarModel?.correoElectronicoId,
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerDireccionEntity() : DireccionesEntity {
    return DireccionesEntity(
        direccionId = this.contactoParaRegistrarModel?.direccionId,
        direccion = this.contactoParaRegistrarModel?.direccion
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoDireccionEntity() : Afiliado_Direccion_Entity {
    return Afiliado_Direccion_Entity(
        registro = this.contactoParaRegistrarModel?.afiliadoDireccionId,
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId,
        direccionId = this.contactoParaRegistrarModel?.direccionId
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerTelefonoEntity() : TelefonosEntity {
    return TelefonosEntity(
        telefonoId = this.contactoParaRegistrarModel?.telefonoId,
        telefono = this.contactoParaRegistrarModel?.telefono,
        tipoTelefonoId = this.contactoParaRegistrarModel?.tipoTelefono?.traerId()
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoTelefonoEntity() : Afiliado_Telefono_Entity {
    return Afiliado_Telefono_Entity(
        registroID = this.contactoParaRegistrarModel?.afiliadoTelefonoId,
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId,
        telefonoId = this.contactoParaRegistrarModel?.telefonoId
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoJacComiteEntity(): Afiliado_Jac_Comite_Entity {
    return Afiliado_Jac_Comite_Entity(
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId,
        comiteId = this.detalleEnJACParaRegistroModel?.comitesEnJAC?.traerId(),
        fechaActualizacion = Date().convertirAFormato(formato = FormatosFecha.ISO_8610)
    )
}

fun CompiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoJACEstadoAfiliadoEntity()  : Afiliado_Jac_EstadoAfiliacionEntity {
    return Afiliado_Jac_EstadoAfiliacionEntity(
        afiliadoId = this.datosBasicosParaRegistrarModel?.afiliadoId,
        rolEnLaAppId = RolesEnApp.PREAFILIADO.traerId(),
        estadoAfiliacionId = this.detalleEnJACParaRegistroModel?.estadoAfiliacion?.traerId(),
        fechaActualizacion = Date().convertirAFormato(formato = FormatosFecha.ISO_8610),
        observaciones = this.detalleEnJACParaRegistroModel?.observacionEstadoAfiliacion
    )
}