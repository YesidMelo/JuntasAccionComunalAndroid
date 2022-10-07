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
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerDetalleUsuarioSesion
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.HelperTraerListaJACSRegistrados
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.JACDisponibleParaAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface  LoginDBDatasource {
    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : Flow<Boolean?>
    fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): Flow<Boolean?>
    fun registrarJAC(jacRegistroModel: JACRegistroModel): Flow<Boolean?>
    fun traerJacsRegistradas(): Flow<List<JACDisponibleParaAfiliadoModel>?>
    fun traerDetalleUsuarioSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) : Flow<UsuarioEnSesionModel>
}

class LoginDBDatasourceImpl constructor(
    @JvmField @Inject var memoriaCacheLocal: MemoriaCache,

    @JvmField @Inject var helperRegistroJAC: HelperRegistroJAC,
    @JvmField @Inject var helperIniciarSesion: HelperIniciarSesion,
    @JvmField @Inject var helperTraerListaJACSRegistrados: HelperTraerListaJACSRegistrados,
    @JvmField @Inject var helperRegistroAfilado: HelperRegistroAfilado,
    @JvmField @Inject var helperTraerDetalleUsuarioSesion: HelperTraerDetalleUsuarioSesion
    ): BaseDBDatasourceImpl(memoriaCache = memoriaCacheLocal), LoginDBDatasource {

    //region variables
    private val listaJacsRegistradas = MutableLiveData<List<JACDisponibleParaAfiliadoModel>?>()
    //endregion


    override fun registrarAfiliado(afiliadoARegistrarModel: AfiliadoARegistrarModel): Flow<Boolean?> {
        return helperRegistroAfilado
            .conAfiliadoARegistrarModel(afiliadoARegistrarModel = afiliadoARegistrarModel)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .registrar()
    }

    override fun registrarJAC(jacRegistroModel: JACRegistroModel): Flow<Boolean?> {
        return helperRegistroJAC
            .conJACRegistroModel(jacRegistroModel = jacRegistroModel)
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .registrarJAC()
    }

    override fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): Flow<Boolean?> {
        return helperIniciarSesion
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .conUsuarioInicioSesionModel(usuarioInicioSesionModel = usuarioInicioSesionModel)
            .iniciarSesion()
    }

    override fun traerJacsRegistradas(): Flow<List<JACDisponibleParaAfiliadoModel>?> {
        return helperTraerListaJACSRegistrados
            .conEscuchadorExcepciones(escuchadorExcepciones = traerExcepcionesLiveData())
            .traerLista()
    }

    override fun traerDetalleUsuarioSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel): Flow<UsuarioEnSesionModel> {
        return helperTraerDetalleUsuarioSesion
            .conUsuarioInicioSesionModel(usuarioInicioSesionModel = usuarioInicioSesionModel)
            .traerDetalleUsuarioSesion()
    }
}