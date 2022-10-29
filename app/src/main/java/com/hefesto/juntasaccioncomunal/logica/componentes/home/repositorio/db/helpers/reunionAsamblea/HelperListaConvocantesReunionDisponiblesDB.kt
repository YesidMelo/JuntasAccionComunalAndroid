package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.reunionAsamblea.ConvocatesDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListConvocanteReunionAsambleaAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface HelperListaConvocantesReunionDisponiblesDB {
    suspend fun traerListaConvocantes(): List<ConvocanteReunionAsambleaAAgendarModel>
}

class HelperListaConvocantesReunionDisponiblesDBImpl constructor(
    @JvmField @Inject var convocatesDao: ConvocatesDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperListaConvocantesReunionDisponiblesDB{

    override suspend fun traerListaConvocantes(): List<ConvocanteReunionAsambleaAAgendarModel> {
        val usuarioEnSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return emptyList()
        val jacId = usuarioEnSesion.jacId?:return emptyList()
        val listaEntidades = convocatesDao.traerListaConvocantesPorJacId(jacId = jacId)
        return listaEntidades.convertirAListConvocanteReunionAsambleaAAgendarModel()
    }

}