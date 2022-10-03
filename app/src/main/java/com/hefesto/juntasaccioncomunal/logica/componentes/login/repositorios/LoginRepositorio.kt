package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasource
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import javax.inject.Inject

interface LoginRepositorio {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
    fun registrarJAC(jacRegistroModel: JACRegistroModel) : MutableLiveData<Boolean?>
    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel) : MutableLiveData<Boolean?>
}

class LoginRepositorioImpl constructor(
    @JvmField @Inject var loginApiDatasource: LoginApiDatasource,
    @JvmField @Inject var loginCacheDatasource: LoginCacheDatasource,
    @JvmField @Inject var loginDBDatasource: LoginDBDatasource,
    @JvmField @Inject var loginSharedPreferencesDatasource: LoginSharedPreferencesDatasource
) : LoginRepositorio {

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> = loginDBDatasource.iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)

    override fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?> = loginDBDatasource.registrarJAC(jacRegistroModel = jacRegistroModel)

    override fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> = loginDBDatasource.registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)

}