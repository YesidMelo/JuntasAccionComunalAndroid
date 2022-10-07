package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAAfiliadoEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperTraerDetalleUsuarioSesion constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao
) {

    //region varriables
    lateinit var usuarioInicioSesionModel: UsuarioInicioSesionModel
    //endregion

    fun conUsuarioInicioSesionModel(usuarioInicioSesionModel: UsuarioInicioSesionModel) : HelperTraerDetalleUsuarioSesion {
        this.usuarioInicioSesionModel = usuarioInicioSesionModel
        return this
    }

    fun traerDetalleUsuarioSesion() = flow<UsuarioEnSesionModel> {
        val afiliadoEnSesionView = afiliadoDao.traerDetalleUsuarioEnSesionPorCorreo(correo = usuarioInicioSesionModel.correo!!)
        val afiliadoEnSesionModel = afiliadoEnSesionView?.convertirAAfiliadoEnSesionModel()
        val elemento = afiliadoEnSesionModel
        emit(UsuarioEnSesionModel(
            afiliadoEnSesionModel = afiliadoEnSesionModel
        ))
    }
}