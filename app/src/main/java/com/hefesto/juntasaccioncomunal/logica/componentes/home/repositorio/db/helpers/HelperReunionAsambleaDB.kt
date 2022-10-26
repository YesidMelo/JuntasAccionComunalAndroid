package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperAgendarReunionDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperCrearActaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaAfiliadosAsistenciaDB
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea.HelperListaReunionesParaCrearActasDB
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperReunionAsambleaDB {
    fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) : Flow<Boolean>
    fun traerListaReunionesParaCrearActa() : Flow<List<ReunionAsambleaCreacionActaModel>>
    fun traerListaAfiliadosAsistencia(): Flow<List<AfiliadoActaAsistenciaModel>>
    fun guardarActa( asistencia: MutableList<AfiliadoActaAsistenciaModel>, detalleReunion: ReunionAsambleaCreacionActaModel ) :  Flow<Boolean>
}

class HelperReunionAsambleaDBImpl constructor(
    @JvmField @Inject var helperAgendarReunionDB : HelperAgendarReunionDB,
    @JvmField @Inject var helperListaReunionesParaCrearActasDB: HelperListaReunionesParaCrearActasDB,
    @JvmField @Inject var helperListaAfiliadosAsistenciaDB: HelperListaAfiliadosAsistenciaDB,
    @JvmField @Inject var helperCrearActaDB: HelperCrearActaDB
) : HelperReunionAsambleaDB {

    override fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean> = flow {
        helperAgendarReunionDB.agendarReunion(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
        emit(true)
    }

    override fun traerListaReunionesParaCrearActa(): Flow<List<ReunionAsambleaCreacionActaModel>> = flow {
        val lista = helperListaReunionesParaCrearActasDB.traerListaReunionesParaCrearActa()
        emit(lista)
    }

    override fun traerListaAfiliadosAsistencia(): Flow<List<AfiliadoActaAsistenciaModel>> = flow {
        val lista = helperListaAfiliadosAsistenciaDB.traerListaAfiliadosAsistencia()
        emit(lista)
    }

    override fun guardarActa(
        asistencia: MutableList<AfiliadoActaAsistenciaModel>,
        detalleReunion: ReunionAsambleaCreacionActaModel
    ): Flow<Boolean> = flow {
        helperCrearActaDB.guardarActa(asistencia = asistencia, detalleReunion = detalleReunion)
        emit(true)
    }

}