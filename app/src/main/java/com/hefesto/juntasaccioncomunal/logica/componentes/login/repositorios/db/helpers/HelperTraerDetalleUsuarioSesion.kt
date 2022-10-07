package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.general.RolApp_FuncionesApp_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAAfiliadoEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.convertirAJACEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperTraerDetalleUsuarioSesion constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var jacDao: JacDao,
    @JvmField @Inject var rolApp_FuncionesApp_Dao : RolApp_FuncionesApp_Dao,
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
        registrarFuncionesPorRol(usuarioEnSesionModel = usuarioEnSesionModel)
        emit(usuarioEnSesionModel)
    }

    //region metodos privados
    private fun registrarAfiliadoSesion(usuarioEnSesionModel: UsuarioEnSesionModel) {
        val afiliadoEnSesionView = afiliadoDao.traerDetalleUsuarioEnSesionPorCorreo(correo = usuarioInicioSesionModel.correo!!)?:return
        usuarioEnSesionModel.afiliadoEnSesionModel = afiliadoEnSesionView.convertirAAfiliadoEnSesionModel()
        usuarioEnSesionModel.afiliadoEnSesionModel?.estadoAfiliacion = EstadoAfiliacion.traerEstadoAfiliacionPorId(id = afiliadoEnSesionView.estadoAfiliacionId)
        usuarioEnSesionModel.rolApp = RolesEnApp.traerRolAppPorId(id = afiliadoEnSesionView.rolEnLaAppId)
    }

    private fun registrarJacEnSesion(usuarioEnSesionModel: UsuarioEnSesionModel) {
        val jacEnSesionView = jacDao.traerJAcEnSesion(correo = usuarioInicioSesionModel.correo!!)?:return
        usuarioEnSesionModel.jacEnSesionModel = jacEnSesionView.convertirAJACEnSesionModel()
        usuarioEnSesionModel.rolApp = RolesEnApp.JAC
    }

    private fun registrarFuncionesPorRol(usuarioEnSesionModel: UsuarioEnSesionModel) {
        if (usuarioEnSesionModel.rolApp == null) return
        val listaFuncionesAppDB= rolApp_FuncionesApp_Dao.traerListaFuncionesAppPorRol(usuarioEnSesionModel.rolApp!!.traerId())
        if (listaFuncionesAppDB.isEmpty()) return

        val listaFuncionesEnum = emptyList<FuncionesRolApp>().toMutableList()
        for(id in listaFuncionesAppDB) {
            val funcionEncontrada = FuncionesRolApp.traerFuncionRolPorId(id = id) ?: continue
            listaFuncionesEnum.add(funcionEncontrada)
        }
        usuarioEnSesionModel.funcionesRolApp = listaFuncionesEnum
    }
    //endregion
}