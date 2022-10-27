package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaPuntoReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.delay
import javax.inject.Inject

interface HelperListaReunionesParaCrearPDFDB {
    suspend fun traerLista() : List<ReunionParaGenerarPDFModel>
}

class HelperListaReunionesParaCrearPDFDBImpl constructor(
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache,
) : HelperListaReunionesParaCrearPDFDB {

    override suspend fun traerLista(): List<ReunionParaGenerarPDFModel> {
        delay(5000)
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return emptyList()
        val jacId = usuarioEnSesionModel.jacId?:return emptyList()
        val lista = reunionAsambleaDao.traerListaReunionesParaGeneracionPDF(jacId = jacId).convertirAListaReunionParaGenerarPDFModel()
        traerPuntosAListaReuniones(lista = lista)
        return lista
    }

    //region metodos privados

    private fun traerPuntosAListaReuniones(lista : List<ReunionParaGenerarPDFModel>) {
        for (item in lista) {
            val reunionId = item.reunionAsambleaId?:continue
            val puntos = puntosReunionDao.traerListaPuntosDeLaReunionPorReunionId(reunionId = reunionId)
            item.listaPuntos = puntos.convertirAListaPuntoReunionParaGenerarPDFModel()
        }
    }

    //endregion

}