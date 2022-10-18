package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT  " +
            "    ace.correoid as correoElectronicoId, " +
            "    c.correo, " +
            "    c.fechaRegistro as fechaRegistroCorreo, " +
            "    ace.afiliadoid, " +
            "    d.direccionId, " +
            "    d.direccion, " +
            "    ade.registro as afiliadoDireccionId, " +
            "    t.telefonoId, " +
            "    t.telefono, " +
            "    t.tipoTelefonoId, " +
            "    ate.registroID  as afiliadoTelefonoId " +
            "from  " +
            "   CorreosEntity c, " +
            "   Afiliado_Correo_Entity ace, " +
            "   DireccionesEntity d, " +
            "   Afiliado_Direccion_Entity ade, " +
            "   TelefonosEntity t, " +
            "   Afiliado_Telefono_Entity ate " +
            "where " +
            " c.registro = ace.correoId and  " +
            " ade.afiliadoId = ace.afiliadoId and " +
            " ade.direccionId = d.direccionId and " +
            " ate.afiliadoId = ace.afiliadoid and " +
            " ate.telefonoId = t.telefonoId " +
            "LIMIT 1"
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
