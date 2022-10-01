package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.excepciones.UsuarioNoEstaRegistradoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LoginCacheDatasource {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
}

class LoginCacheDatasourceImpl constructor(
    @JvmField @Inject var baseCacheDatasource: BaseCacheDatasource
) : LoginCacheDatasource {

    //region variables
    private var inicioSesionExitosa = MutableLiveData<Boolean?>()
    //endregion

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> {
        GlobalScope.launch {
            inicioSesionExitosa.postValue(null)
            delay(5000)
            inicioSesionExitosa.postValue(false)
            baseCacheDatasource.traerExcepcionesLiveData().postValue(UsuarioNoEstaRegistradoExcepcion())
        }
        return inicioSesionExitosa
    }

}