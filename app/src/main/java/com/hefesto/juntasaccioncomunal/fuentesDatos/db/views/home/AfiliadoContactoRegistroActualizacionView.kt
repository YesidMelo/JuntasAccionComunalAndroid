package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView(
    "select  " +
    "correo.*,  " +
    "direccion.direccionid, direccion.direccion, direccion.afiliadoDireccionId, " +
    "telefono.telefonoid, telefono.telefono, telefono.tipotelefonoid, telefono.afiliadoTelefonoId " +
    "from  " +
    "(SELECT c.registro as correoElectronicoId, c.correo, c.fecharegistro as fechaRegistroCorreo, ace.afiliadoId from CorreosEntity c, Afiliado_Correo_Entity ace where c.registro = ace.correoId) " +
    "correo, " +
    "(SELECT d.direccionid, d.direccion, ade.registro as afiliadoDireccionId, ade.afiliadoId from DireccionesEntity d, Afiliado_Direccion_Entity ade WHERE ade.direccionId = d.direccionId) " +
    "direccion, " +
    "(SELECT t.telefonoid, t.telefono, t.tipoTelefonoId, ate.registroID as afiliadoTelefonoId, ate.afiliadoId  from TelefonosEntity t, Afiliado_Telefono_Entity ate WHERE ate.telefonoId = t.telefonoId) " +
    "telefono " +
    "WHERE " +
    "direccion.afiliadoid = correo.afiliadoid and " +
    "correo.afiliadoid = telefono.afiliadoid"
)
data class AfiliadoContactoRegistroActualizacionView(
    var correoElectronicoId : Int? = null,
    var correo : String? = null,
    var fechaRegistroCorreo: String? = null,
    var afiliadoid: Int? = null,
    var direccionId: Int? = null,
    var direccion: String? = null,
    var afiliadoDireccionId: Int? = null,
    var telefonoId: Int? = null,
    var telefono: String? = null,
    var tipoTelefonoId: Int? = null,
    var afiliadoTelefonoId: Int? = null
)
