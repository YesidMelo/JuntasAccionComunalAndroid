package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HomeCacheDatasource {
    fun cargarFuncionalidades(): Flow<List<FuncionesRolApp>>
    fun traerJacId() : Flow<Int?>
}

class HomeCacheDatasourceImpl constructor(
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HomeCacheDatasource {

    override fun cargarFuncionalidades(): Flow<List<FuncionesRolApp>> = flow{
        val usuarioSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)
        if (usuarioSesion == null) { emit(emptyList()); return@flow }
        if (usuarioSesion.funcionesRolApp.isNullOrEmpty()){ emit(emptyList()); return@flow }
        emit(usuarioSesion.funcionesRolApp!!)
    }

    override fun traerJacId(): Flow<Int?> = flow{
        val usuarioSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)
        if (usuarioSesion == null) { emit(null); return@flow }
        if (usuarioSesion.jacId == null){ emit(null); return@flow }
        emit(usuarioSesion.jacId)
    }

}