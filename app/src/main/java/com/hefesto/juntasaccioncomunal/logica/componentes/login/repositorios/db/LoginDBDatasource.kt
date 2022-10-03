package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseDBDatasourceImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperIniciarSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroAfilado
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperRegistroJAC
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerListaJACSRegistrados
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import javax.inject.Inject

interface  LoginDBDatasource {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : MutableLiveData<Boolean?>
    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?>
    fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?>
    fun traerJacsRegistradas(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>
}

class LoginDBDatasourceImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var memoriaCacheLocal: MemoriaCache,
    @JvmField @Inject var jacDao: JacDao,
    ): BaseDBDatasourceImpl(memoriaCache = memoriaCacheLocal), LoginDBDatasource {

    //region variables
    private val registroExitosoJAC = MutableLiveData<Boolean?>()
    private val registroExitosoAfiliado = MutableLiveData<Boolean?>()
    private val inicioSesionExitoso = MutableLiveData<Boolean?>()
    private val listaJacsRegistradas = MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>()
    //endregion


    override fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> {
        HelperRegistroAfilado(
            afiliadoARegistrarModel = afiliadoARegistrarModel,
            escuchadorExcepciones = traerExcepcionesLiveData(),
            escuchadorRegistroAfiliadoExitoso = registroExitosoAfiliado,
            afiliadoDao = afiliadoDao
        ).registrar()
        return registroExitosoAfiliado
    }

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

    override fun traerJacsRegistradas(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?> {
        HelperTraerListaJACSRegistrados(
            listaJacsRegistradas = listaJacsRegistradas,
            jacDao = jacDao,
            escuchadorExcepciones = traerExcepcionesLiveData()
        ).traerLista()
        return listaJacsRegistradas
    }
}