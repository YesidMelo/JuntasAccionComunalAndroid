package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasource
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseCacheDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.excepciones.UsuarioNoEstaRegistradoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import javax.inject.Inject

interface LoginCacheDatasource {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean>
}

class LoginCacheDatasourceImpl constructor(
    @JvmField @Inject var baseCacheDatasource: BaseCacheDatasource
) : LoginCacheDatasource {

    //region variables
    private var inicioSesionExitosa = MutableLiveData<Boolean>()
    //endregion

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean> {
        Handler().postDelayed({
            inicioSesionExitosa.value = false
            baseCacheDatasource.traerExcepcionesLiveData().value = UsuarioNoEstaRegistradoExcepcion()
        }, 5000)
        return inicioSesionExitosa
    }

}