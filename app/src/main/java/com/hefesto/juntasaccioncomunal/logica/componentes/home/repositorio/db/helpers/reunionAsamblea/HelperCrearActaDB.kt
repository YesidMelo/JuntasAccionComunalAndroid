package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ListaAsistenciaDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaAsistenciaEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaPuntosReunionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import javax.inject.Inject

interface HelperCrearActaDB {
    suspend fun guardarActa(
        asistencia: MutableList<AfiliadoActaAsistenciaModel>,
        detalleReunion: ReunionAsambleaCreacionActaModel
    )
}

class HelperCrearActaDBImpl constructor(
    @JvmField @Inject var reunionDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var listaAsambleaDao: ListaAsistenciaDao
) : HelperCrearActaDB {

    override suspend fun guardarActa(
        asistencia: MutableList<AfiliadoActaAsistenciaModel>,
        detalleReunion: ReunionAsambleaCreacionActaModel
    ) {

        val listaAsistenciaEntity = asistencia.convertirAListaAsistenciaEntity(reunionId = detalleReunion.reunionAsambleaId!!)
        val listaPuntos = detalleReunion.listaPuntos!!.convertirAListaPuntosReunionEntity()

        val reunionEntity = detalleReunion.convertirAReunionAsambleaEntity()
        reunionEntity.creoActa = true

        reunionDao.insertarElemento(elemento = reunionEntity)
        puntosReunionDao.insertarElementos(elementos =  listaPuntos)
        listaAsambleaDao.insertarElementos(elementos = listaAsistenciaEntity)
    }

}