package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAPuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.delay
import javax.inject.Inject

interface HelperListaReunionesParaCrearActasDB {
    suspend fun traerListaReunionesParaCrearActa() : List<ReunionAsambleaCreacionActaModel>
}

class HelperListaReunionesParaCrearActasDBImpl constructor(
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache,
) : HelperListaReunionesParaCrearActasDB {

    override suspend fun traerListaReunionesParaCrearActa(): List<ReunionAsambleaCreacionActaModel> {
        delay(5000)
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return emptyList()
        val jacId = usuarioEnSesionModel.jacId?:return emptyList()

        val lista = emptyList<ReunionAsambleaCreacionActaModel>().toMutableList()
        cargarListaActas(listaReuniones = lista, jacId = jacId)
        cargarPuntosReunion(listaReuniones = lista)
        return lista
    }

    //region metodos privados
    private fun cargarListaActas(listaReuniones: MutableList<ReunionAsambleaCreacionActaModel>, jacId: Int) {
        val listaEntity = reunionAsambleaDao.traerListaReunionesParaCreacionActas(jacId = jacId)
        val listaModels = listaEntity.convertirAListaReunionAsambleaCreacionActaModel()
        listaModels.forEach { listaReuniones.add(it) }
    }

    private fun cargarPuntosReunion(listaReuniones: MutableList<ReunionAsambleaCreacionActaModel>) {
        for(item in listaReuniones) {
            if (item.reunionAsambleaId == null) continue
            val listaPuntosEntity = puntosReunionDao.traerListaPuntosDeLaReunionPorReunionId(reunionId = item.reunionAsambleaId!!)
            item.listaPuntos = listaPuntosEntity.convertirAPuntoReunionParaCreacionActaModel()
        }
    }
    //endregion

}