package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ListaAsistenciaDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaPuntoReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ImagenesStringBase64
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.delay
import javax.inject.Inject

interface HelperListaReunionesParaCrearPDFDB {
    suspend fun traerLista() : List<ReunionParaGenerarPDFModel>
}

class HelperListaReunionesParaCrearPDFDBImpl constructor(
    @JvmField @Inject var listaAsistenciaDao: ListaAsistenciaDao,
    @JvmField @Inject var convocantesDao: ConvocatesDao,
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache,
) : HelperListaReunionesParaCrearPDFDB {

    override suspend fun traerLista(): List<ReunionParaGenerarPDFModel> {
        delay(5000)
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return emptyList()
        val jacId = usuarioEnSesionModel.jacId?:return emptyList()
        val lista = reunionAsambleaDao.traerListaReunionesParaGeneracionPDF(jacId = jacId).convertirAListaReunionParaGenerarPDFModel()
        ponerMarcaAgua(lista = lista)
        ponerConvocantes(lista = lista)
        traerNumeroAsistentes(lista = lista)
        traerPuntosAListaReuniones(lista = lista)
        return lista
    }

    //region metodos privados

    //todo eliminar este metodo. se debe consumir desde db o servidor
    private fun ponerMarcaAgua(lista : List<ReunionParaGenerarPDFModel>) {
        for (item in lista) {
            item.marcaAgua = ImagenesStringBase64.LogoBosaSantaBarbara
        }
    }

    private fun ponerConvocantes(lista : List<ReunionParaGenerarPDFModel>) {
        if (lista.isEmpty()) return
        for (item in lista) {
            val reunionId = item.reunionAsambleaId?:continue
            val listaConvocantes = convocantesDao.traerListaConvocantesActaPorReunionId(reunionId = reunionId)
            item.listaConvocantes = listaConvocantes
        }
    }

    private fun traerNumeroAsistentes(lista : List<ReunionParaGenerarPDFModel>)  {
        if (lista.isEmpty()) return
        for (item in lista) {
            item.numeroAsistentes = listaAsistenciaDao.traerNumeroAsistentesPorReunionId(reunionId = item.reunionAsambleaId!!)
        }
    }

    private fun traerPuntosAListaReuniones(lista : List<ReunionParaGenerarPDFModel>) {
        for (item in lista) {
            val reunionId = item.reunionAsambleaId?:continue
            val puntos = puntosReunionDao.traerListaPuntosDeLaReunionPorReunionId(reunionId = reunionId)
            item.listaPuntos = puntos.convertirAListaPuntoReunionParaGenerarPDFModel()
        }
    }

    //endregion

}