package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
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
    @JvmField @Inject var memoriaCacheLocal: MemoriaCache,

    @JvmField @Inject var helperRegistroJAC: HelperRegistroJAC,
    @JvmField @Inject var helperIniciarSesion: HelperIniciarSesion,
    @JvmField @Inject var helperTraerListaJACSRegistrados: HelperTraerListaJACSRegistrados,
    @JvmField @Inject var helperRegistroAfilado: HelperRegistroAfilado,
    ): BaseDBDatasourceImpl(memoriaCache = memoriaCacheLocal), LoginDBDatasource {

    //region variables
    private val registroExitosoJAC = MutableLiveData<Boolean?>()
    private val registroExitosoAfiliado = MutableLiveData<Boolean?>()
    private val inicioSesionExitoso = MutableLiveData<Boolean?>()
    private val listaJacsRegistradas = MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>()
    //endregion


    override fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): MutableLiveData<Boolean?> {
        helperRegistroAfilado
            .conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel)
            .conEscuchadorRegistroAfiliadoExitoso(escuchadorRegistroAfiliadoExitoso = registroExitosoAfiliado)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .registrar()
        return registroExitosoAfiliado
    }

    override fun registrarJAC(jacRegistroModel: JACRegistroModel): MutableLiveData<Boolean?> {
        helperRegistroJAC
            .conRegistroExitosoLiveData(registroExitosoJAC= registroExitosoJAC)
            .conJACRegistroModel(jacRegistroModel = jacRegistroModel)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .registrarJAC()
        return registroExitosoJAC
    }

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): MutableLiveData<Boolean?> {
        helperIniciarSesion
            .conInicioSesionExitosa(inicioSesionExitosa = inicioSesionExitoso)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .conUsuarioInicioSesionModel(usuarioInicioSesionModel = usuarioInicioSesionModel)
            .iniciarSesion()
        return inicioSesionExitoso
    }

    override fun traerJacsRegistradas(): MutableLiveData<List<JACDisponibleParaAfiliadoModel>?> {
        helperTraerListaJACSRegistrados
            .conListaJacsRegistradas(listaJacsRegistradas = listaJacsRegistradas)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .traerLista()
        return listaJacsRegistradas
    }
}