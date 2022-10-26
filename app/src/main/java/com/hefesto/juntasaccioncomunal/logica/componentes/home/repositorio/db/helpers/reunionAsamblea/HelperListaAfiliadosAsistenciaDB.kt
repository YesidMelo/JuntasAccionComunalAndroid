package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.reunionAsamblea

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaAfiliadoAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface HelperListaAfiliadosAsistenciaDB {
    suspend fun traerListaAfiliadosAsistencia(): List<AfiliadoActaAsistenciaModel>
}

class HelperListaAfiliadosAsistenciaDBImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperListaAfiliadosAsistenciaDB {

    override suspend fun traerListaAfiliadosAsistencia(): List<AfiliadoActaAsistenciaModel> {
        val usuarioEnSesionModel = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?: return emptyList()
        val jacId = usuarioEnSesionModel.jacId?: return emptyList()
        val listasView = afiliadoDao.traerAfiliadosParaAsistencia(jacId = jacId)
        val lista = listasView.convertirAListaAfiliadoAsistenciaModel()
        return lista
    }

}