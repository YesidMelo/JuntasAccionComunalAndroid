package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion

class TipoReunionModel(
    val tipoReunion: TipoReunion,
    val nombre: String
) : BaseModel() {
    override fun toString(): String = nombre
}
