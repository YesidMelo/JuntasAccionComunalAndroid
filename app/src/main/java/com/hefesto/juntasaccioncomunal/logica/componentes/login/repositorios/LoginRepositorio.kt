package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import javax.inject.Inject

interface LoginRepositorio {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
}

class LoginRepositorioImpl constructor(
    @JvmField @Inject var loginApiDatasource: LoginApiDatasource,
    @JvmField @Inject var loginCacheDatasource: LoginCacheDatasource,
    @JvmField @Inject var loginDBDatasource: LoginDBDatasource,
    @JvmField @Inject var loginSharedPreferencesDatasource: LoginSharedPreferencesDatasource
) : LoginRepositorio {

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> = loginCacheDatasource.iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)
}