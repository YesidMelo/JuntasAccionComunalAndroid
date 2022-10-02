package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import javax.inject.Inject

interface  LoginDBDatasource {
    fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?>
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
}

class LoginDBDatasourceImpl constructor(
    @JvmField @Inject var memoriaCacheLocal: MemoriaCache,
    @JvmField @Inject var jacDao: JacDao
    ): BaseDBDatasourceImpl(memoriaCache = memoriaCacheLocal), LoginDBDatasource {

    //region variables
    private val registroExitosoJAC = MutableLiveData<Boolean?>()
    private val inicioSesionExitoso = MutableLiveData<Boolean?>()
    //endregion

    override fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?> {
        HelperRegistroJAC(
            jacRegistroModel = jacRegistroModel,
            registroExitosoJAC = registroExitosoJAC,
            escuchadorExcepciones = traerExcepcionesLiveData(),
            jacDao = jacDao
        ).registrarJAC()
        return registroExitosoJAC
    }

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> {
        HelperIniciarSesion(
            jacDao = jacDao,
            usuarioInicioSesionModel = usuarioInicioSesionModel,
            escuchadorExcepciones = traerExcepcionesLiveData(),
            inicioSesionExitosa = inicioSesionExitoso
        ).iniciarSesion()
        return inicioSesionExitoso
    }
}