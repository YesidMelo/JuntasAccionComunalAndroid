package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerListaConvocantesEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerListaPuntosReunion
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface HelperAgendarReunionDB {
    suspend fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel)
}

class HelperAgendarReunionDBImpl constructor(
    @JvmField @Inject var convocatesDao: ConvocatesDao,
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache,
) : HelperAgendarReunionDB {

    override suspend fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) {
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return
        val jacId = usuarioEnSesionModel.jacId?:return
        detalleReunionAAgendarModel.jacId = jacId
        registrarReunion(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
        registrarPunto(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
        registrarConvocantes(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
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

    private fun registrarConvocantes(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) {
        val listaConvocantesEntity = detalleReunionAAgendarModel.traerListaConvocantesEntity()
        convocatesDao.insertarElementos(elementos = listaConvocantesEntity)
    }
    //endregion

}