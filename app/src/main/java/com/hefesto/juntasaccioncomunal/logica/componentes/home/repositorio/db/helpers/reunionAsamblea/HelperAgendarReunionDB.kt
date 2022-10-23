package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerListaPuntosReunion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.DetalleReunionAAgendarModel
import javax.inject.Inject

interface HelperAgendarReunionDB {
    suspend fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel)
}

class HelperAgendarReunionDBImpl constructor(
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
) : HelperAgendarReunionDB {

    override suspend fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) {
        registrarReunion(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
        registrarPunto(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
    }

    //region metodos privados
    private fun registrarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) {
        val asambleaEntity = detalleReunionAAgendarModel.convertirAReunionAsambleaEntity()
        val id = reunionAsambleaDao.insertarElemento(elemento = asambleaEntity).toInt()
        detalleReunionAAgendarModel.reunionAsambleaId = id

    }

    private fun registrarPunto(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) {
        val listaPuntosEntity = detalleReunionAAgendarModel.traerListaPuntosReunion()
        puntosReunionDao.insertarElementos(elementos = listaPuntosEntity)
    }
    //endregion

}