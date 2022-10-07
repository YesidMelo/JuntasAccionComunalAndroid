package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface LoginCacheDatasource {
    fun guardarDetalleUsuarioSesion(detalleUsuarioEnSesionModel: UsuarioEnSesionModel)
}

class LoginCacheDatasourceImpl constructor(
    @JvmField @Inject var baseCacheDatasource: BaseCacheDatasource
) : LoginCacheDatasource {

    override fun guardarDetalleUsuarioSesion(detalleUsuarioEnSesionModel: UsuarioEnSesionModel) {
        baseCacheDatasource
            .traerMemoriaCache()
            .AdicionarActualizarElemento(par = Pair(
                first = IdentificadorElementosCacheEnum.USUARIO_EN_SESION,
                second = detalleUsuarioEnSesionModel
            ))
    }
}