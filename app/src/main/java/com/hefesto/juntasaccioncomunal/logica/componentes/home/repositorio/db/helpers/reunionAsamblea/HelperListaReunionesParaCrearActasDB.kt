package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.delay

interface HelperListaReunionesParaCrearActasDB {
    suspend fun traerListaReunionesParaCrearActa() : List<ReunionAsambleaCreacionActaModel>
}

class HelperListaReunionesParaCrearActasDBImpl : HelperListaReunionesParaCrearActasDB {

    override suspend fun traerListaReunionesParaCrearActa(): List<ReunionAsambleaCreacionActaModel> {
        delay(5000)
        val lista = emptyList<ReunionAsambleaCreacionActaModel>().toMutableList()
        return lista
    }

}