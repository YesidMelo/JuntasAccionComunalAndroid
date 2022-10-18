package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.listaAfiliadosRegistroActualizacion

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.convertirAListaDatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperListaAfiliadosCargarDatosBasicos {
    fun traerListaAfiliados() : Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
}

class HelperListaAfiliadosCargarDatosBasicosImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperListaAfiliadosCargarDatosBasicos{

    override fun traerListaAfiliados(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?> = flow {
        val jacId = traerJacId()
        if (jacId == null) {
            emit(emptyList())
            return@flow
        }
        val listaEntidades = afiliadoDao.traerListaAfiliadosParaActualizacionRegistroEntity(jacID = jacId)
        emit(listaEntidades.convertirAListaDatosBasicosAfiliadoActualizarRegistrarInformacionModel())
    }

    //region metodos privados
    private fun traerJacId() : Int? {
        val jacId = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)
        return jacId?.jacId
    }
    //endregion
}