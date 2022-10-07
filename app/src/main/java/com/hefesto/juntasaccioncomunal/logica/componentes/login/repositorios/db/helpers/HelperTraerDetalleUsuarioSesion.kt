package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAAfiliadoEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAJACEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperTraerDetalleUsuarioSesion constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var jacDao: JacDao
) {

    //region varriables
    lateinit var usuarioInicioSesionModel: UsuarioInicioSesionModel
    //endregion

    fun conUsuarioInicioSesionModel(usuarioInicioSesionModel: UsuarioInicioSesionModel) : HelperTraerDetalleUsuarioSesion {
        this.usuarioInicioSesionModel = usuarioInicioSesionModel
        return this
    }

    fun traerDetalleUsuarioSesion() = flow<UsuarioEnSesionModel> {
        val usuarioEnSesionModel = UsuarioEnSesionModel()
        registrarAfiliadoSesion(usuarioEnSesionModel = usuarioEnSesionModel)
        registrarJacEnSesion(usuarioEnSesionModel = usuarioEnSesionModel)
        emit(usuarioEnSesionModel)
    }

    //region metodos privados
    private fun registrarAfiliadoSesion(usuarioEnSesionModel: UsuarioEnSesionModel) {
        val afiliadoEnSesionView = afiliadoDao.traerDetalleUsuarioEnSesionPorCorreo(correo = usuarioInicioSesionModel.correo!!)
        usuarioEnSesionModel.afiliadoEnSesionModel = afiliadoEnSesionView?.convertirAAfiliadoEnSesionModel()
    }

    private fun registrarJacEnSesion(usuarioEnSesionModel: UsuarioEnSesionModel) {
        val jacEnSesionView = jacDao.traerJAcEnSesion(correo = usuarioInicioSesionModel.correo!!)?:return
        usuarioEnSesionModel.jacEnSesionModel = jacEnSesionView.convertirAJACEnSesionModel()
        usuarioEnSesionModel.rolApp = RolesEnApp.JAC
    }
    //endregion
}