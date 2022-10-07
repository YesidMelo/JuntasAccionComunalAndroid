package com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios

import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.helpers.HelperCargarTipos
import javax.inject.Inject

interface SplashDBDatasource {
    suspend fun cargarTipos()
}

class SplashDBDatasourceImpl constructor(
    @JvmField @Inject var helperCargarTipos : HelperCargarTipos
) : SplashDBDatasource {


    override suspend fun cargarTipos() {
        helperCargarTipos.cargarEstadosAfiliacion()
        helperCargarTipos.cargarFuncionesApp()
        helperCargarTipos.cargarRelacionesRolAppFuncionesApp()
        helperCargarTipos.cargarRolAfiliacion()
        helperCargarTipos.cargarRolesApp()
        helperCargarTipos.cargarTiposDocumento()
        helperCargarTipos.cargarTiposTelefono()
    }
}