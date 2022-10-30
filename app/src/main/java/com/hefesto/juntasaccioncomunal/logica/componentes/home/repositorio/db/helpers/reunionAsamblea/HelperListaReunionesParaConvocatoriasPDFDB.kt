package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.PuntosReunionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ReunionAsambleaDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaPuntoReunionGeneracionConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.PuntoReunionGeneracionConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.constantes.ImagenesStringBase64
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface HelperListaReunionesParaConvocatoriasPDFDB {
    suspend fun traerListaReunionesParaGenerarConvocatoria(): List<ReunionParaGenerarConvocatoriaPDFModel>
}

class HelperListaReunionesParaConvocatoriasPDFDBImpl constructor(
    @JvmField @Inject var reunionAsambleaDao: ReunionAsambleaDao,
    @JvmField @Inject var puntosReunionDao: PuntosReunionDao,
    @JvmField @Inject var convocantesDao: ConvocatesDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperListaReunionesParaConvocatoriasPDFDB {

    override suspend fun traerListaReunionesParaGenerarConvocatoria(): List<ReunionParaGenerarConvocatoriaPDFModel> {
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return emptyList()
        val jacId = usuarioEnSesionModel.jacId?:return emptyList()
        val lista = reunionAsambleaDao.traerListaReunionesParaCreacionActas(jacId = jacId).convertirAListReunionParaGenerarConvocatoriaPDFModel()
        //todo eliminar este metodo esta imagen debe venir del servidor
        ponerLogoBosaSantabarbara(lista = lista)
        traerListaPuntos(lista = lista)
        traerListaConvocantes(lista = lista)
        return lista
    }

    //region metodos privados

    //todo eliminar este metodo el logo debe venir del servidor o de db
    private fun ponerLogoBosaSantabarbara(lista : List<ReunionParaGenerarConvocatoriaPDFModel>) {
        lista.forEach {
            it.logoJAC = ImagenesStringBase64.LogoBosaSantaBarbara
        }
    }

    private fun traerListaPuntos(lista : List<ReunionParaGenerarConvocatoriaPDFModel>) {
        if (lista.isEmpty()) return

        for(item in lista) {
            val reunionId = item.reunionAsambleaId?:continue
            val puntos = puntosReunionDao.traerListaPuntosDeLaReunionPorReunionId(reunionId = reunionId).convertirAListaPuntoReunionGeneracionConvocatoriaPDFModel()
            item.listaPuntos = puntos
        }
    }

    private fun traerListaConvocantes(lista : List<ReunionParaGenerarConvocatoriaPDFModel>) {
        if (lista.isEmpty()) return
        for (item in lista) {
            val reunionId = item.reunionAsambleaId?:continue
            val listaConvocantes = convocantesDao.traerListaConvocantesPorReunionId(reunionId = reunionId)
            item.listaConvocantes = listaConvocantes
        }
    }
    //endregion

}