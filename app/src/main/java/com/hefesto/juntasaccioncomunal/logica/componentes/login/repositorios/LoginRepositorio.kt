package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.LoginDBDatasource
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LoginRepositorio {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
    fun registrarJAC(jacRegistroModel: JACRegistroModel) : MutableLiveData<Boolean?>
    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel) : MutableLiveData<Boolean?>
    fun traerJacsRegistradas(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>
}

class LoginRepositorioImpl constructor(
    @JvmField @Inject var loginApiDatasource: LoginApiDatasource,
    @JvmField @Inject var loginCacheDatasource: LoginCacheDatasource,
    @JvmField @Inject var loginDBDatasource: LoginDBDatasource,
    @JvmField @Inject var loginSharedPreferencesDatasource: LoginSharedPreferencesDatasource
) : LoginRepositorio {

    //region variables
    private val iniciarSesionLiveData = MutableLiveData<Boolean?>()
    private val escuchadorRegistroAfiliadoExitoso = MutableLiveData<Boolean?>()
    private val registroExitosoJAC = MutableLiveData<Boolean?>()
    private val listaJACLivedata = MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>()
    //endregion

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> {
        GlobalScope.launch {
            loginDBDatasource
                .iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)
                .collect {

                    if (it == null) { iniciarSesionLiveData.postValue(it); return@collect }
                    if (!it) { iniciarSesionLiveData.postValue(it); return@collect }
                    loginDBDatasource
                        .traerDetalleUsuarioSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)
                        .collect{
                            usuarioEnSesionModel ->
                            loginCacheDatasource
                                .guardarDetalleUsuarioSesion(detalleUsuarioEnSesionModel = usuarioEnSesionModel)
                            iniciarSesionLiveData.postValue(it)
                        }
                }
        }
        return iniciarSesionLiveData
    }

    override fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?> {
        GlobalScope.launch {
            loginDBDatasource
                .registrarJAC(jacRegistroModel = jacRegistroModel)
                .collect{registroExitosoJAC.postValue(it)}
        }
        return registroExitosoJAC
    }

    override fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> {
        GlobalScope.launch {
            loginDBDatasource
                .registrarAfiliado(afiliadoARegistrarModel = afiliadoARegistrarModel)
                .collect { escuchadorRegistroAfiliadoExitoso.postValue(it)}
        }
        return escuchadorRegistroAfiliadoExitoso
    }

    override fun traerJacsRegistradas(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?> {
        GlobalScope.launch {
            loginDBDatasource
                .traerJacsRegistradas()
                .collect{ listaJACLivedata.postValue(it)}
        }
        return listaJACLivedata
    }

}