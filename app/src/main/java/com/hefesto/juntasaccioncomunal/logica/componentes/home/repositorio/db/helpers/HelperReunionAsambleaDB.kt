package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperAgendarReunionDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearActasDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperReunionAsambleaDB {
    fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) : Flow<Boolean>
    fun traerListaReunionesParaCrearActa() : Flow<List<ReunionAsambleaCreacionActaModel>>
}

class HelperReunionAsambleaDBImpl constructor(
    @JvmField @Inject var helperAgendarReunionDB : HelperAgendarReunionDB,
    @JvmField @Inject var helperListaReunionesParaCrearActasDB: HelperListaReunionesParaCrearActasDB
) : HelperReunionAsambleaDB {

    override fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean> = flow {
        helperAgendarReunionDB.agendarReunion(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
        emit(true)
    }

    override fun traerListaReunionesParaCrearActa(): Flow<List<ReunionAsambleaCreacionActaModel>> = flow {
        val lista = helperListaReunionesParaCrearActasDB.traerListaReunionesParaCrearActa()
        emit(lista)
    }

}